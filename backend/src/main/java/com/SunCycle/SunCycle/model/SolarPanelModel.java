package com.SunCycle.SunCycle.model;

import jakarta.persistence.*;

@Entity
@Table(name = "solar_panel_model")
public class SolarPanelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private int id;

    private double polymers;
    private double silicon;
    private double copper;
    private double glass;
    private double silver;
    private double aluminium;

    // constructors
    public SolarPanelModel() {}

    public SolarPanelModel(double polymers, double silicon, double copper, double glass, double silver, double aluminium) {
        this.polymers = polymers;
        this.silicon = silicon;
        this.copper = copper;
        this.glass = glass;
        this.silver = silver;
        this.aluminium = aluminium;
    }

    // getters
    public int getId() {
        return id;
    }

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
    public void setId(int id) {
        this.id = id;
    }

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
