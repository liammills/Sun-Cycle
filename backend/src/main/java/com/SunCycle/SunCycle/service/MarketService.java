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
import com.SunCycle.SunCycle.utils.GeocodeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public MarketResponseDTO searchUserQueryPanels(MarketRequestDTO dto) {
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

        // compute result map
        Map<SolarPanelInstallation, List<SolarPanel>> result = new HashMap<>();

        for (SolarPanel panel: resultPanels) {
            SolarPanelInstallation installation = panel.getInstallation();
            result.computeIfAbsent(
                    installation,
                    k -> new ArrayList<>()
            ).add(panel);
        }

        return new MarketResponseDTO(result, Status.SUCCESS);
    }

    // find solar panels by model
    private List<SolarPanel> findPanelsByModel(MarketRequestDTO dto) {
        SolarPanelModel model = solarPanelModelRepository
                .findSolarPanelModelByPolymersAndSiliconAndCopperAndGlassAndSilverAndAluminium(
                        dto.getPolymers(),
                        dto.getSilicon(),
                        dto.getCopper(),
                        dto.getGlass(),
                        dto.getSilver(),
                        dto.getAluminium()
                );

        return solarPanelRepository.findSolarPanelsBySolarPanelModel(model);
    }

    // find solar panels by method and date
    private List<SolarPanel> findPanelsByMethodAndDate(MarketRequestDTO dto) {
        return solarPanelRepository
                .findSolarPanelsByRecyclingMethodAndRetirementDate(
                        dto.getRecyclingMethod(),
                        dto.getRetirementDate()
                );
    }

    // find solar panels by location
    private List<SolarPanel> findPanelsByLocation(MarketRequestDTO dto) {
        // get lat, lng for the query location
        GeocodeResponse queryLocation = getGeocodeResponse(dto.getCity() + ", " + dto.getState() + ", AU");
        double queryLat = queryLocation.getLat();
        double queryLng = queryLocation.getLng();

        // check if each installation satisfies the location requirement
        List<SolarPanelInstallation> goodInstallations = (List<SolarPanelInstallation>) solarPanelInstallationRepository.findAll();
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

        // return a list of panels that are associated with good installations
        List<SolarPanel> result = new ArrayList<>();
        for (SolarPanel panel: solarPanelRepository.findAll()) {
            if ( goodInstallations.contains(panel.getInstallation()) ) {
                result.add(panel);
            }
        }

        return result;
    }

    // map response instance
    private GeocodeResponse getGeocodeResponse(String address) {
        // get response
        String jsonResponse = getGeocodeData(address);
        ObjectMapper mapper = new ObjectMapper();

        // parse response
        try {
            return mapper.readValue(jsonResponse, GeocodeResponse.class);
        } catch (Exception e) {
            return null;
        }
    }

    // google map api helper
    private String getGeocodeData(String address) {
        // init REST template
        RestTemplate restTemplate = new RestTemplate();

        // replace all spaces by +
        address = address.replace(' ', '+');

        // setup request URL
        String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json";
        String finalUrl = baseUrl + "?address=" + address + "&key=" + apiKey;

        // get response as a long string
        ResponseEntity<String> response = restTemplate.getForEntity(finalUrl, String.class);
        return response.getBody();
    }

    private double haversineDistance(double lat1, double lng1, double lat2, double lng2) {
        int earthRadius = 6371; // kilometers

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c; // returns distance in kilometers
    }

}
