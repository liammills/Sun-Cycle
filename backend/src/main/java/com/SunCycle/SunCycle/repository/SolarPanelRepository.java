package com.SunCycle.SunCycle.repository;

import com.SunCycle.SunCycle.model.SolarPanel;
import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.model.SolarPanelModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface SolarPanelRepository extends CrudRepository<SolarPanel, Integer> {
    List<SolarPanel> findSolarPanelsBySolarPanelInstallation(SolarPanelInstallation installation);
    List<SolarPanel> findSolarPanelsBySolarPanelModel(SolarPanelModel model);
    List<SolarPanel> findSolarPanelsByRetirementDate(Date date);
}
