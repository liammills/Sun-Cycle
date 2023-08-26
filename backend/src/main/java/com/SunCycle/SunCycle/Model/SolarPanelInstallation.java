package com.SunCycle.SunCycle.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
public class SolarPanelInstallation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "User ID is mandatory")
    private int userId;


    private String geoLocation;
    private String address;
    private String state;
    private int postcode;
    private String type;

    // Constructors, getters, setters, etc.

    public SolarPanelInstallation() {
    }

    public SolarPanelInstallation(int userId, String geoLocation, String address,
                                  String state, int postcode, String type) {
        this.userId = userId;
        this.geoLocation = geoLocation;
        this.address = address;
        this.state = state;
        this.postcode = postcode;
        this.type = type;
    }

    // Getters and setters for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            if (this == o)
                return true;

            return ((User) o).getId() == this.getId();
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
