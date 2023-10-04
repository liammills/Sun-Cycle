package com.SunCycle.SunCycle.model;

public class SolarPanelInstallationDTO {

    private SolarPanelInstallation solarPanelInstallation;
    private String message;

    private Status status;
    public SolarPanelInstallationDTO(SolarPanelInstallation solarPanelInstallation, String message) {
        this.solarPanelInstallation = solarPanelInstallation;
        this.message = message;
    }

    public SolarPanelInstallationDTO(SolarPanelInstallation solarPanelInstallation, Status status) {
        this.solarPanelInstallation = solarPanelInstallation;
        this.status = status;
    }

    public SolarPanelInstallationDTO(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public SolarPanelInstallation getSolarPanelInstallation() {
        return solarPanelInstallation;
    }

    public String getMessage() {
        return message;
    }
}
