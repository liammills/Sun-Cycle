package com.SunCycle.SunCycle.dto;

import com.SunCycle.SunCycle.model.SolarPanel;

public class SolarPanelDTO {

    // request fields
    private int modelId;
    private int installationId;
    private String installationDate;
    private String retirementDate;
    private String recyclingMethod;

    // response fields
    private SolarPanel solarPanel;
    private String message;
    private Status status;

    // default constructor
    public SolarPanelDTO() {}

    // constructors for request data
    public SolarPanelDTO(int modelId, int installationId, String installationDate,
                         String retirementDate, String recyclingMethod) {
        this.modelId = modelId;
        this.installationId = installationId;
        this.installationDate = installationDate;
        this.retirementDate = retirementDate;
        this.recyclingMethod = recyclingMethod;
    }

    // constructors for response data
    public SolarPanelDTO(SolarPanel solarPanel, String message) {
        this.solarPanel = solarPanel;
        this.message = message;
    }

    public SolarPanelDTO(SolarPanel solarPanel, Status status) {
        this.solarPanel = solarPanel;
        this.status = status;
    }

    public SolarPanelDTO(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    // getters and setters for request fields
    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getInstallationId() {
        return installationId;
    }

    public void setInstallationId(int installationId) {
        this.installationId = installationId;
    }

    public String getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(String installationDate) {
        this.installationDate = installationDate;
    }

    public String getRetirementDate() {
        return retirementDate;
    }

    public void setRetirementDate(String retirementDate) {
        this.retirementDate = retirementDate;
    }

    public String getRecyclingMethod() {
        return recyclingMethod;
    }

    public void setRecyclingMethod(String recyclingMethod) {
        this.recyclingMethod = recyclingMethod;
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
