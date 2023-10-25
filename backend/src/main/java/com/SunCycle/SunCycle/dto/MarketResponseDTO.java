package com.SunCycle.SunCycle.dto;

import com.SunCycle.SunCycle.model.SolarPanel;

import java.util.Map;

public class MarketResponseDTO {

    private SolarPanel solarPanel;
    private Map<String, Double> geoLocation;

    // constructors
    public MarketResponseDTO(SolarPanel solarPanel, Map<String, Double> geoLocation) {
        this.solarPanel = solarPanel;
        this.geoLocation = geoLocation;
    }

    // response getters and setters
    public SolarPanel getSolarPanel() {
        return solarPanel;
    }

    public Map<String, Double> getGeoLocation() {
        return geoLocation;
    }

    public void setSolarPanel(SolarPanel solarPanel) {
        this.solarPanel = solarPanel;
    }

    public void setGeoLocation(Map<String, Double> geoLocation) {
        this.geoLocation = geoLocation;
    }

}
