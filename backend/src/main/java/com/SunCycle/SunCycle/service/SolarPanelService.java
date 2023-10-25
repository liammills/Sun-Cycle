package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.SolarPanelRequestDTO;
import com.SunCycle.SunCycle.dto.SolarPanelResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.*;
import com.SunCycle.SunCycle.repository.SolarPanelInstallationRepository;
import com.SunCycle.SunCycle.repository.SolarPanelModelRepository;
import com.SunCycle.SunCycle.repository.SolarPanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SolarPanelService {

    @Autowired
    private SolarPanelRepository solarPanelRepository;

    @Autowired
    private SolarPanelModelRepository solarPanelModelRepository;

    @Autowired
    private SolarPanelInstallationRepository solarPanelInstallationRepository;

    public List<SolarPanel> getPanelsByInstallation(int id) {
        // get installation by id
        Optional<SolarPanelInstallation> installationOpt = solarPanelInstallationRepository.findById(id);
        if (installationOpt.isEmpty()) {
            return null;
        }

        // get all panels associated with the installation
        SolarPanelInstallation installation = installationOpt.get();
        return solarPanelRepository.findSolarPanelsBySolarPanelInstallation(installation);
    }

    public SolarPanelResponseDTO createPanel(SolarPanelRequestDTO dto) {
        // convert dto to panel instance
        SolarPanel panel = new SolarPanel();

        // check if model and installation exists
        Optional<SolarPanelModel> model = solarPanelModelRepository.findById(dto.getModelId());
        if (model.isPresent())
            panel.setModel(model.get());
        else
            return new SolarPanelResponseDTO("Panel model not found", Status.NOT_FOUND);

        Optional<SolarPanelInstallation> installation = solarPanelInstallationRepository.findById(dto.getInstallationId());
        if (installation.isPresent())
            panel.setInstallation(installation.get());
        else
            return new SolarPanelResponseDTO("Installation not found", Status.NOT_FOUND);

        // set other fields
        panel.setInstallationDate(dto.getInstallationDate());
        panel.setRetirementDate(dto.getRetirementDate());

        // return response DTO
        return new SolarPanelResponseDTO(solarPanelRepository.save(panel), Status.SUCCESS);
    }

    public SolarPanelResponseDTO updatePanel(int panelId, SolarPanelRequestDTO dto) {
        // try to fetch panel by given id
        Optional<SolarPanel> panelOpt = solarPanelRepository.findById(panelId);

        if (panelOpt.isEmpty())
            return new SolarPanelResponseDTO("Panel not found", Status.NOT_FOUND);

        SolarPanel panel = panelOpt.get();

        // update panel
        // check if new model exists
        Optional<SolarPanelModel> model = solarPanelModelRepository.findById(dto.getModelId());
        if (model.isEmpty())
            return new SolarPanelResponseDTO("Model not found", Status.NOT_FOUND);
        panel.setModel(model.get());

        // check if new installation exists
        Optional<SolarPanelInstallation> installation = solarPanelInstallationRepository.findById(dto.getInstallationId());
        if (installation.isEmpty())
            return new SolarPanelResponseDTO("Installation not found", Status.NOT_FOUND);
        panel.setInstallation(installation.get());

        // update the rest fields
        panel.setInstallationDate(dto.getInstallationDate());
        panel.setRetirementDate(dto.getRetirementDate());

        return new SolarPanelResponseDTO(solarPanelRepository.save(panel), Status.SUCCESS);
    }

    public SolarPanelResponseDTO deletePanel(int panelId) {
        // try to fetch panel by given id
        Optional<SolarPanel> panelOpt = solarPanelRepository.findById(panelId);

        if (panelOpt.isEmpty())
            return new SolarPanelResponseDTO("Panel not found", Status.NOT_FOUND);

        solarPanelRepository.delete(panelOpt.get());

        return new SolarPanelResponseDTO("Panel deleted successfully", Status.SUCCESS);
    }

}
