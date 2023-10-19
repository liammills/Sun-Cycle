package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.SolarPanelModelRequestDTO;
import com.SunCycle.SunCycle.dto.SolarPanelModelResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.SolarPanelModel;
import com.SunCycle.SunCycle.repository.SolarPanelModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolarPanelModelService {

    @Autowired
    private SolarPanelModelRepository solarPanelModelRepository;

    // get all
    public List<SolarPanelModel> getAllModels() {
        return (List<SolarPanelModel>) solarPanelModelRepository.findAll();
    }

    // get by id
    public SolarPanelModelResponseDTO getModelById(int id) {
        Optional<SolarPanelModel> modelOpt = solarPanelModelRepository.findById(id);

        return modelOpt.map(solarPanelModel ->
                new SolarPanelModelResponseDTO(solarPanelModel, Status.SUCCESS)).orElseGet(
                        () -> new SolarPanelModelResponseDTO("Solar panel model not found", Status.NOT_FOUND));
    }

    // post
    public SolarPanelModelResponseDTO createModel(SolarPanelModelRequestDTO dto) {
        // check if model exists
        Optional<SolarPanelModel> modelOpt = Optional.ofNullable(solarPanelModelRepository.
                findSolarPanelModelByPolymersAndSiliconAndCopperAndGlassAndSilverAndAluminium(
                        dto.getPolymers(),
                        dto.getSilicon(),
                        dto.getCopper(),
                        dto.getGlass(),
                        dto.getSilver(),
                        dto.getAluminium()
                ));

        if (modelOpt.isPresent())
            return new SolarPanelModelResponseDTO("Solar panel model exists", Status.ERROR);

        // create new model
        SolarPanelModel model = new SolarPanelModel(
                dto.getPolymers(),
                dto.getSilicon(),
                dto.getCopper(),
                dto.getGlass(),
                dto.getSilver(),
                dto.getAluminium()
        );

        // insert new model into repo
        return new SolarPanelModelResponseDTO(solarPanelModelRepository.save(model), Status.SUCCESS);
    }

    // put
    public SolarPanelModelResponseDTO updateModel(int id, SolarPanelModelRequestDTO dto) {
        // check if model exists
        Optional<SolarPanelModel> modelOpt = solarPanelModelRepository.findById(id);

        if (modelOpt.isEmpty())
            return new SolarPanelModelResponseDTO("Solar panel model not found", Status.NOT_FOUND);

        // update the model
        SolarPanelModel model = modelOpt.get();
        model.setAluminium(dto.getAluminium());
        model.setCopper(dto.getCopper());
        model.setGlass(dto.getGlass());
        model.setPolymers(dto.getPolymers());
        model.setSilicon(dto.getSilicon());
        model.setSilver(dto.getSilver());

        return new SolarPanelModelResponseDTO(solarPanelModelRepository.save(model), Status.SUCCESS);
    }

    // delete
    public SolarPanelModelResponseDTO deleteModel(int id) {
        // check if model exists
        Optional<SolarPanelModel> modelOpt = solarPanelModelRepository.findById(id);

        if (modelOpt.isEmpty())
            return new SolarPanelModelResponseDTO("Solar panel model not found", Status.NOT_FOUND);

        // delete the model
        solarPanelModelRepository.deleteById(id);

        return new SolarPanelModelResponseDTO(modelOpt.get(), Status.SUCCESS);
    }

}
