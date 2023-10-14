package com.SunCycle.SunCycle.repository;

import com.SunCycle.SunCycle.model.SolarPanelModel;
import org.springframework.data.repository.CrudRepository;

public interface SolarPanelModelRepository extends CrudRepository<SolarPanelModel, Integer> {
    SolarPanelModel findSolarPanelModelByPolymersAndSiliconAndCopperAndGlassAndSilverAndAluminium(
            double polymers,
            double silicon,
            double copper,
            double glass,
            double silver,
            double aluminium
    );
}
