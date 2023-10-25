package com.SunCycle.SunCycle.repository;

import com.SunCycle.SunCycle.model.SolarPanelModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolarPanelModelRepository extends CrudRepository<SolarPanelModel, Integer> {

    List<SolarPanelModel> findSolarPanelModelsByRecyclingMethodAndPolymersGreaterThanEqualAndSiliconGreaterThanEqualAndCopperGreaterThanEqualAndGlassGreaterThanEqualAndSilverGreaterThanEqualAndAluminiumGreaterThanEqual(
            String recyclingMethod,
            double polymers,
            double silicon,
            double copper,
            double glass,
            double silver,
            double aluminium
    );

    SolarPanelModel findSolarPanelModelByModelNameAndRecyclingMethodAndPolymersAndSiliconAndCopperAndGlassAndSilverAndAluminium(
            String modelName,
            String recyclingMethod,
            double polymers,
            double silicon,
            double copper,
            double glass,
            double silver,
            double aluminium
    );

}
