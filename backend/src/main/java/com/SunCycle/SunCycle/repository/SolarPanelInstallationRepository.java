package com.SunCycle.SunCycle.repository;

import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolarPanelInstallationRepository extends CrudRepository<SolarPanelInstallation, Integer> {
    List<SolarPanelInstallation> findSolarPanelInstallationsByUserId(int userId);
}
