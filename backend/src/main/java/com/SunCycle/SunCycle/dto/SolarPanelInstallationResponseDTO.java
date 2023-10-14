package com.SunCycle.SunCycle.dto;

import com.SunCycle.SunCycle.model.SolarPanelInstallation;

public class SolarPanelInstallationResponseDTO {

    private SolarPanelInstallation solarPanelInstallation;
    private String message;
    private Status status; // Assuming Status is an Enum or another class

    public SolarPanelInstallationResponseDTO(SolarPanelInstallation solarPanelInstallation, String message) {
        this.solarPanelInstallation = solarPanelInstallation;
        this.message = message;
    }

    public SolarPanelInstallationResponseDTO(SolarPanelInstallation solarPanelInstallation, Status status) {
        this.solarPanelInstallation = solarPanelInstallation;
        this.status = status;
    }

    public SolarPanelInstallationResponseDTO(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    // getters and setters for response fields
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public SolarPanelInstallation getSolarPanelInstallation() {
        return solarPanelInstallation;
    }

    public void setSolarPanelInstallation(SolarPanelInstallation solarPanelInstallation) {
        this.solarPanelInstallation = solarPanelInstallation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
