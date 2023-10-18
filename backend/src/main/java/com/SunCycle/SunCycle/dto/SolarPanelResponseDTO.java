package com.SunCycle.SunCycle.dto;

import com.SunCycle.SunCycle.model.SolarPanel;

public class SolarPanelResponseDTO {
    // response fields
    private SolarPanel solarPanel;
    private String message;
    private Status status;

    // constructors for response data

    public SolarPanelResponseDTO() {
    }

    public SolarPanelResponseDTO(SolarPanel solarPanel, String message) {
        this.solarPanel = solarPanel;
        this.message = message;
    }

    public SolarPanelResponseDTO(SolarPanel solarPanel, Status status) {
        this.solarPanel = solarPanel;
        this.status = status;
    }

    public SolarPanelResponseDTO(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    // getters and setters for response fields
    public SolarPanel getSolarPanel() {
        return solarPanel;
    }

    public void setSolarPanel(SolarPanel solarPanel) {
        this.solarPanel = solarPanel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
