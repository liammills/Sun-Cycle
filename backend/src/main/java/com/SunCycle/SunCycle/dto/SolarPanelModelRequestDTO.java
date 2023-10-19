package com.SunCycle.SunCycle.dto;

public class SolarPanelModelRequestDTO {

    private double polymers;
    private double silicon;
    private double copper;
    private double glass;
    private double silver;
    private double aluminium;

    // constructors
    public SolarPanelModelRequestDTO() {}

    public SolarPanelModelRequestDTO(double polymers, double silicon, double copper, double glass, double silver, double aluminium) {
        this.polymers = polymers;
        this.silicon = silicon;
        this.copper = copper;
        this.glass = glass;
        this.silver = silver;
        this.aluminium = aluminium;
    }

    // getters
    public double getPolymers() {
        return polymers;
    }

    public double getSilicon() {
        return silicon;
    }

    public double getCopper() {
        return copper;
    }

    public double getGlass() {
        return glass;
    }

    public double getSilver() {
        return silver;
    }

    public double getAluminium() {
        return aluminium;
    }

    // setters
    public void setPolymers(double polymers) {
        this.polymers = polymers;
    }

    public void setSilicon(double silicon) {
        this.silicon = silicon;
    }

    public void setCopper(double copper) {
        this.copper = copper;
    }

    public void setGlass(double glass) {
        this.glass = glass;
    }

    public void setSilver(double silver) {
        this.silver = silver;
    }

    public void setAluminium(double aluminium) {
        this.aluminium = aluminium;
    }

}
