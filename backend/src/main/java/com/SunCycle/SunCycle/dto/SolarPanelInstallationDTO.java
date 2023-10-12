package com.SunCycle.SunCycle.dto;

import com.SunCycle.SunCycle.model.SolarPanelInstallation;

public class SolarPanelInstallationDTO {

    // request fields
    private int userId;
    private String geoLocation;
    private String address;
    private String state;
    private int postcode;
    private String type;

    // response fields
    private SolarPanelInstallation solarPanelInstallation;
    private String message;
    private Status status; // Assuming Status is an Enum or another class

    // default constructor
    public SolarPanelInstallationDTO() {
    }

    // constructors for request fields
    public SolarPanelInstallationDTO(int userId, String geoLocation, String address,
                                     String state, int postcode, String type) {
        this.userId = userId;
        this.geoLocation = geoLocation;
        this.address = address;
        this.state = state;
        this.postcode = postcode;
        this.type = type;
    }

    // getters and setters for request fields
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // constructors for response fields
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
