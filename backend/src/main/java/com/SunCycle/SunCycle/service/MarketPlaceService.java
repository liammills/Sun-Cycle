package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.repository.SolarPanelInstallationRepository;
import com.SunCycle.SunCycle.repository.SolarPanelModelRepository;
import com.SunCycle.SunCycle.repository.SolarPanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<SolarPanelInstallation> searchUserQueryPanels(Map<String, ?> query) {
        List<SolarPanelInstallation> result = new ArrayList<>();

        // get user query
        String method = (String) query.get("method");

        return result;
    }

    public List<SolarPanelInstallation> searchPopularPanels() {
        List<SolarPanelInstallation> result = new ArrayList<>();

        return result;
    }

}
