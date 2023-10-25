package com.SunCycle.SunCycle.dto;

import com.SunCycle.SunCycle.model.SolarPanel;

import java.util.List;

public class MarketResponseDTO {

    private List<SolarPanel> result;
    private String message;
    private Status status;

    // constructors
    public MarketResponseDTO(List<SolarPanel> result, Status status) {
        this.result = result;
        this.status = status;
    }

    public MarketResponseDTO(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    // response getters and setters
    public List<SolarPanel> getResult() {
        return result;
    }

    public void setResult(List<SolarPanel> result) {
        this.result = result;
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
