package com.SunCycle.SunCycle.dto;

public class SolarPanelInstallationRequestDTO {

    // request fields
    private int userId;
    private String address;
    private String state;
    private int postcode;
    private String type;

    // default constructor
    public SolarPanelInstallationRequestDTO() {
    }

    // constructors for request fields
    public SolarPanelInstallationRequestDTO(int userId, String address,
                                            String state, int postcode, String type) {
        this.userId = userId;
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

}
