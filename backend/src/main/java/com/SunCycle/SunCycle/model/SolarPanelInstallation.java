package com.SunCycle.SunCycle.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "solar_panel_installation")
public class SolarPanelInstallation {

    @Id
    @Column(name = "installation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    private String geoLocation;
    private String address;
    private String state;
    private int postcode;
    private String type;

    // Constructors, getters, setters, etc.

    public SolarPanelInstallation() {
    }

    public SolarPanelInstallation(User user, String geoLocation, String address,
                                  String state, int postcode, String type) {
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

            return ((User) o).getUserId() == this.getId();
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void update(SolarPanelInstallation installation) {
        this.setGeoLocation(installation.getGeoLocation());
        this.setAddress(installation.getAddress());
        this.setPostcode(installation.getPostcode());
        this.setState(installation.getState());
        this.setType(installation.getType());
    }

}
