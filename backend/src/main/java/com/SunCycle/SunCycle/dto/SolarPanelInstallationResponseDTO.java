package com.SunCycle.SunCycle.dto;

import com.SunCycle.SunCycle.model.SolarPanel;
import com.SunCycle.SunCycle.model.SolarPanelInstallation;

import java.util.List;

public class SolarPanelInstallationResponseDTO {

    private SolarPanelInstallation solarPanelInstallation;
    private List<SolarPanel> solarPanels;
    private String message;
    private Status status;

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

    public List<SolarPanel> getSolarPanels() {
        return solarPanels;
    }

    public void setSolarPanels(List<SolarPanel> solarPanels) {
        this.solarPanels = solarPanels;
    }

}
