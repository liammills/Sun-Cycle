package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.MarketRequestDTO;
import com.SunCycle.SunCycle.dto.MarketResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.SolarPanel;
import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.model.SolarPanelModel;
import com.SunCycle.SunCycle.repository.SolarPanelInstallationRepository;
import com.SunCycle.SunCycle.repository.SolarPanelModelRepository;
import com.SunCycle.SunCycle.repository.SolarPanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarketPlaceService {

    @Autowired
    private SolarPanelInstallationRepository solarPanelInstallationRepository;

    @Autowired
    private SolarPanelModelRepository solarPanelModelRepository;

    @Autowired
    private SolarPanelRepository solarPanelRepository;

    // post
    public MarketResponseDTO searchUserQueryPanels(MarketRequestDTO dto) {
        // find model by breakdown
        SolarPanelModel model = solarPanelModelRepository.findSolarPanelModelByPolymersAndSiliconAndCopperAndGlassAndSilverAndAluminium(
                dto.getPolymers(),
                dto.getSilicon(),
                dto.getCopper(),
                dto.getGlass(),
                dto.getSilver(),
                dto.getAluminium()
        );

        // find panels by model
        List<SolarPanel> panels = solarPanelRepository.findSolarPanelsBySolarPanelModel(model);

        // filter panels by user query location
        // TODO

        // compute result map
        Map<SolarPanelInstallation, List<SolarPanel>> result = new HashMap<>();

        for (SolarPanel panel : panels) {
            SolarPanelInstallation installation = panel.getInstallation();
            result
                    .computeIfAbsent(installation, k -> new ArrayList<>())
                    .add(panel);
        }

        return new MarketResponseDTO(result, Status.SUCCESS);
    }

    // get
    public MarketResponseDTO searchPopularPanels() {
        return null;
    }

    // google map api helper


}
