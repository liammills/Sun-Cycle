package com.SunCycle.SunCycle.model;

import jakarta.persistence.*;

@Entity
@Table(name = "solar_panel_model")
public class SolarPanelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private int id;

    private float mass;
    private float silicon;
    private float copper;
    private float glass;
    private float silver;
    private float aluminium;

    // constructor
    public SolarPanelModel() {}

    // getters
    public int getId() {
        return id;
    }

    public float getMass() {
        return mass;
    }

    public float getSilicon() {
        return silicon;
    }

    public float getCopper() {
        return copper;
    }

    public float getGlass() {
        return glass;
    }

    public float getSilver() {
        return silver;
    }

    public float getAluminium() {
        return aluminium;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public void setSilicon(float silicon) {
        this.silicon = silicon;
    }

    public void setCopper(float copper) {
        this.copper = copper;
    }

    public void setGlass(float glass) {
        this.glass = glass;
    }

    public void setSilver(float silver) {
        this.silver = silver;
    }

    public void setAluminium(float aluminium) {
        this.aluminium = aluminium;
    }

}
