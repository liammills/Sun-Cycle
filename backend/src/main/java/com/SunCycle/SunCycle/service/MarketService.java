package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.MarketRequestDTO;
import com.SunCycle.SunCycle.dto.MarketResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.SolarPanel;
import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.model.SolarPanelModel;
import com.SunCycle.SunCycle.repository.SolarPanelInstallationRepository;
import com.SunCycle.SunCycle.repository.SolarPanelModelRepository;
import com.SunCycle.SunCycle.repository.SolarPanelRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class MarketService {

    @Autowired
    private SolarPanelInstallationRepository solarPanelInstallationRepository;

    @Autowired
    private SolarPanelModelRepository solarPanelModelRepository;

    @Autowired
    private SolarPanelRepository solarPanelRepository;

    @Value("${google.maps.api.key}")
    private String apiKey;

    // post
    public List<MarketResponseDTO> searchUserQueryPanels(MarketRequestDTO dto) {
        // find model by breakdown (model)
        List<SolarPanel> breakdownFilteredPanels = findPanelsByModel(dto);

        // filter panels by recycling method and retirementDate
        List<SolarPanel> methodAndDateFilteredPanels = findPanelsByMethodAndDate(dto);

        // filter panels by user query location
        List<SolarPanel> locationFilteredPanels = findPanelsByLocation(dto);

        // take panels exist in all three result lists
        List<SolarPanel> resultPanels = breakdownFilteredPanels.stream()
                .filter(methodAndDateFilteredPanels::contains)
                .filter(locationFilteredPanels::contains)
                .distinct()
                .toList();

        // for every panel, get their geoLocations
        List<MarketResponseDTO> response = populateGeoLocations(resultPanels);

        return response;
    }

    // find solar panels by model
    public List<SolarPanel> findPanelsByModel(MarketRequestDTO dto) {
        List<SolarPanelModel> models = solarPanelModelRepository
                .findSolarPanelModelsByRecyclingMethodAndPolymersGreaterThanEqualAndSiliconGreaterThanEqualAndCopperGreaterThanEqualAndGlassGreaterThanEqualAndSilverGreaterThanEqualAndAluminiumGreaterThanEqual(
                        dto.getRecyclingMethod(),
                        dto.getPolymers(),
                        dto.getSilicon(),
                        dto.getCopper(),
                        dto.getGlass(),
                        dto.getSilver(),
                        dto.getAluminium()
                );

        List<SolarPanel> result = new ArrayList<>();

        for (SolarPanelModel model: models) {
            result.addAll(solarPanelRepository.findSolarPanelsBySolarPanelModel(model));
        }

        return result;
    }

    // find solar panels by date
    private List<SolarPanel> findPanelsByMethodAndDate(MarketRequestDTO dto) {
        return solarPanelRepository
                .findSolarPanelsByRetirementDate(
                        dto.getRetirementDate()
                );
    }

    // find solar panels by location
    private List<SolarPanel> findPanelsByLocation(MarketRequestDTO dto) {
        // get lat, lng for the query location
        double[] queryGeoLocation = getLatAndLng(dto.getCity() + ", " + dto.getState() + ", AU");
        assert queryGeoLocation != null;
        double queryLat = queryGeoLocation[0];
        double queryLng = queryGeoLocation[1];

        // check if each installation satisfies the location requirement
        List<SolarPanelInstallation> goodInstallations = (List<SolarPanelInstallation>) solarPanelInstallationRepository.findAll();
        assert !goodInstallations.isEmpty();
        for (SolarPanelInstallation installation: solarPanelInstallationRepository.findAll()) {
            // get lat. lng for the installation location
            double instLat = Double.parseDouble(installation.getGeoLocation().split(",")[0]);
            double instLng = Double.parseDouble(installation.getGeoLocation().split(",")[1]);

            // calculate the distance to see if it satisfies the requirement
            double distance = haversineDistance(queryLat, queryLng, instLat, instLng);

            if (distance >= 10000.0) {
                goodInstallations.remove(installation);
            }
        }

        // compute a list of installation ids
        List<Integer> installationIds = new ArrayList<>();
        for (SolarPanelInstallation installation: goodInstallations) {
            installationIds.add(installation.getId());
        }

        // return a list of panels that are associated with good installations
        List<SolarPanel> result = new ArrayList<>();
        for (SolarPanel panel: solarPanelRepository.findAll()) {
            if ( installationIds.contains(panel.getInstallation().getId()) ) {
                result.add(panel);
            }
        }

        return result;
    }

    // google map api helper
    private double[] getLatAndLng(String address) {
        // init REST template
        RestTemplate restTemplate = new RestTemplate();

        // replace all spaces by +
        address = address.replace(' ', '+');

        // setup request URL
        String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json";
        String finalUrl = baseUrl + "?address=" + address + "&key=" + apiKey;

        // get response as a long string
        String response = restTemplate.getForEntity(finalUrl, String.class).getBody();

        // parse json string into readable object
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;

        try {
            root = mapper.readTree(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace(System.out);
            return null;
        }

        // get lat, lng
        double lat = root.get("results").get(0).get("geometry").get("location").get("lat").asDouble();
        double lng = root.get("results").get(0).get("geometry").get("location").get("lng").asDouble();

        return new double[]{lat, lng};
    }

    private double haversineDistance(double lat1, double lng1, double lat2, double lng2) {
        int earthRadius = 6371; // km

        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

    private List<MarketResponseDTO> populateGeoLocations(List<SolarPanel> panels) {
        List<MarketResponseDTO> resultList = new ArrayList<>();

        for (SolarPanel panel: panels) {
            String[] parts = panel.getInstallation().getGeoLocation().split(",");
            Double lat = Double.parseDouble(parts[0]);
            Double lng = Double.parseDouble(parts[1]);

            Map<String, Double> locationMap = new HashMap<>();
            locationMap.put("lat", lat);
            locationMap.put("lng", lng);

            MarketResponseDTO geoData = new MarketResponseDTO(panel, locationMap);

            resultList.add(geoData);
        }

        return resultList;
    }

}
