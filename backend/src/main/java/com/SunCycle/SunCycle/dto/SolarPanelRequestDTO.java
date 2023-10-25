package com.SunCycle.SunCycle.dto;

public class SolarPanelRequestDTO {

    // request fields
    private int modelId;
    private int installationId;
    private String installationDate;
    private String retirementDate;


    // default constructor
    public SolarPanelRequestDTO() {}

    // constructors for request data
    public SolarPanelRequestDTO(int modelId, int installationId,
                                String installationDate, String retirementDate) {
        this.modelId = modelId;
        this.installationId = installationId;
        this.installationDate = installationDate;
        this.retirementDate = retirementDate;
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

}
