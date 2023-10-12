package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.SolarPanelDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.*;
import com.SunCycle.SunCycle.repository.SolarPanelInstallationRepository;
import com.SunCycle.SunCycle.repository.SolarPanelModelRepository;
import com.SunCycle.SunCycle.repository.SolarPanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SolarPanelService {

    @Autowired
    private SolarPanelRepository solarPanelRepository;

    @Autowired
    private SolarPanelModelRepository solarPanelModelRepository;

    @Autowired
    private SolarPanelInstallationRepository solarPanelInstallationRepository;

    public SolarPanelDTO createPanel(SolarPanelDTO dto) {
        // convert dto to panel instance
        SolarPanel panel = new SolarPanel();

        // check if model and installation exists
        Optional<SolarPanelModel> model = solarPanelModelRepository.findById(dto.getModelId());
        if (model.isPresent())
            panel.setModel(model.get());
        else
            return new SolarPanelDTO("Panel model not found", Status.NOT_FOUND);

        Optional<SolarPanelInstallation> installation = solarPanelInstallationRepository.findById(dto.getInstallationId());
        if (installation.isPresent())
            panel.setInstallation(installation.get());
        else
            return new SolarPanelDTO("Installation not found", Status.NOT_FOUND);

        // set other fields
        panel.setInstallationDate(dto.getInstallationDate());
        panel.setRetirementDate(dto.getRetirementDate());
        panel.setRecyclingMethod(dto.getRecyclingMethod());

        // return response DTO
        return new SolarPanelDTO(solarPanelRepository.save(panel), Status.SUCCESS);
    }

//    public SolarPanelDTO updatePanel(int panelId, SolarPanel newPanel) {
//        Optional<SolarPanel> panelOpt = solarPanelRepository.findById(panelId); // Using provided panelId for fetching.
//
//        if (panelOpt.isEmpty()) {
//            return new SolarPanelDTO("Panel not found", Status.NOT_FOUND);
//        }
//
//        SolarPanel panel = panelOpt.get();
//
//        panel.setInstallationDate(newPanel.getInstallationDate());
//        panel.setRetirementDate(newPanel.getRetirementDate());
//        panel.setRecyclingMethod(newPanel.getRecyclingMethod());
//        panel.setInstallation(newPanel.getInstallation());
//        panel.setModel(newPanel.getModel());
//
//        return prepareResponseDTO(panel);
//    }
//
//    public SolarPanelDTO deletePanel(int panelId) {
//        Optional<SolarPanel> panelOpt = solarPanelRepository.findById(panelId);
//
//        if (panelOpt.isEmpty()) {
//            return new SolarPanelDTO("Panel not found", Status.NOT_FOUND);
//        }
//
//        solarPanelRepository.delete(panelOpt.get());
//
//        return new SolarPanelDTO("Panel deleted successfully", Status.SUCCESS);
//    }

}
