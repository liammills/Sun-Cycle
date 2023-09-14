package com.SunCycle.SunCycle.Repository;

import com.SunCycle.SunCycle.Model.SolarPanelInstallation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolarPanelInstallationRepository extends CrudRepository<SolarPanelInstallation, Integer> {
    List<SolarPanelInstallation> findSolarPanelInstallationsByUserId(int userId);
}
