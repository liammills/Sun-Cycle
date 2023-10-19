package com.SunCycle.SunCycle.dto;

import com.SunCycle.SunCycle.model.SolarPanelModel;

public class SolarPanelModelResponseDTO {

    private SolarPanelModel solarPanelModel;
    private String message;
    private Status status;

    // constructors
    public SolarPanelModelResponseDTO() {}

    public SolarPanelModelResponseDTO(SolarPanelModel solarPanelModel, Status status) {
        this.solarPanelModel = solarPanelModel;
        this.status = status;
    }

    public SolarPanelModelResponseDTO(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    // getters
    public SolarPanelModel getSolarPanelModel() {
        return solarPanelModel;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }

    // setters
    public void setSolarPanelModel(SolarPanelModel solarPanelModel) {
        this.solarPanelModel = solarPanelModel;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
