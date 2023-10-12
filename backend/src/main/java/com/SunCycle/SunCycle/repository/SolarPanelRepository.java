package com.SunCycle.SunCycle.repository;

import com.SunCycle.SunCycle.model.SolarPanel;
import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolarPanelRepository extends CrudRepository<SolarPanel, Integer> {
    List<SolarPanel> findSolarPanelsBySolarPanelInstallation(SolarPanelInstallation installation);
}
