package com.SunCycle.SunCycle.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "solar_panel")
public class SolarPanel {

    @Id
    @Column(name = "panel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "installation_id", referencedColumnName = "installation_id")
    private SolarPanelInstallation solarPanelInstallation;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "model_id")
    private SolarPanelModel solarPanelModel;

    @Column(name = "installation_date")
    private Date installationDate;

    @Column(name = "retirement_date")
    private Date retirementDate;

    @Column(name = "quantity")
    private int quantity;

    // constructor
    public SolarPanel() {}

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int panelId) {
        this.id = panelId;
    }

    public SolarPanelInstallation getInstallation() {
        return solarPanelInstallation;
    }

    public void setInstallation(SolarPanelInstallation solarPanelInstallation) {
        this.solarPanelInstallation = solarPanelInstallation;
    }

    public SolarPanelModel getModel() {
        return solarPanelModel;
    }

    public void setModel(SolarPanelModel solarPanelModel) {
        this.solarPanelModel = solarPanelModel;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(String installationDateStr) {
        try { installationDate = stringToDate(installationDateStr); } catch (ParseException ignored) {}
    }

    public Date getRetirementDate() {
        return retirementDate;
    }

    public void setRetirementDate(String retirementDateStr) {
        try { retirementDate = stringToDate(retirementDateStr); } catch (ParseException ignored) {}
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // helper
    private Date stringToDate(String dateStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(dateStr);
    }

}
