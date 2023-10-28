package com.SunCycle.SunCycle.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetGeoLocation {

    @Value("${google.maps.api.key}")
    private String apiKey;

    public double[] getLatAndLng(String address) {
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
}
