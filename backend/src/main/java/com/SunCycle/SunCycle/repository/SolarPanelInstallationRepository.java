package com.SunCycle.SunCycle.repository;

import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolarPanelInstallationRepository extends CrudRepository<SolarPanelInstallation, Integer> {
    List<SolarPanelInstallation> findSolarPanelInstallationsByUser(User user);
}
