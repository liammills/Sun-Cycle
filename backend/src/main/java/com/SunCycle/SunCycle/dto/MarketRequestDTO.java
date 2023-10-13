package com.SunCycle.SunCycle.dto;

import java.util.Map;

public class MarketRequestDTO {

    private String recyclingMethod;
    private String retirementDate;
    private String city;
    private String state;
    private Map<Breakdown, Double> breakdown;

    // constructors
    public MarketRequestDTO() {}

    public MarketRequestDTO(String recyclingMethod, String retirementDate, String city, String state, Map<Breakdown, Double> breakdown) {
        this.recyclingMethod = recyclingMethod;
        this.retirementDate = retirementDate;
        this.city = city;
        this.state = state;
        this.breakdown = breakdown;
    }

    // getters and setters
    public String getRecyclingMethod() {
        return recyclingMethod;
    }

    public void setRecyclingMethod(String recyclingMethod) {
        this.recyclingMethod = recyclingMethod;
    }

    public String getRetirementDate() {
        return retirementDate;
    }

    public void setRetirementDate(String retirementDate) {
        this.retirementDate = retirementDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPolymers() {
        return breakdown.get(Breakdown.POLYMERS);
    }

    public void setPolymers(double polymers) {
        breakdown.put(Breakdown.POLYMERS, polymers);
    }

    public double getSilicon() {
        return breakdown.get(Breakdown.SILICON);
    }

    public void setSilicon(double silicon) {
        breakdown.put(Breakdown.SILICON, silicon);
    }

    public double getCopper() {
        return breakdown.get(Breakdown.COPPER);
    }

    public void setCopper(double copper) {
        breakdown.put(Breakdown.COPPER, copper);
    }

    public double getGlass() {
        return breakdown.get(Breakdown.GLASS);
    }

    public void setGlass(double glass) {
        breakdown.put(Breakdown.GLASS, glass);
    }

    public double getSilver() {
        return breakdown.get(Breakdown.SILVER);
    }

    public void setSilver(double silver) {
        breakdown.put(Breakdown.SILVER, silver);
    }

    public double getAluminium() {
        return breakdown.get(Breakdown.ALUMINIUM);
    }

    public void setAluminium(double aluminium) {
        breakdown.put(Breakdown.ALUMINIUM, aluminium);
    }

}
