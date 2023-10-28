package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.MarketRequestDTO;
import com.SunCycle.SunCycle.model.SolarPanel;
import com.SunCycle.SunCycle.model.SolarPanelModel;
import com.SunCycle.SunCycle.repository.SolarPanelInstallationRepository;
import com.SunCycle.SunCycle.repository.SolarPanelModelRepository;
import com.SunCycle.SunCycle.repository.SolarPanelRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
@ActiveProfiles("dev")
class MarketServiceTest {

    @Autowired
    private MarketService marketService;

    @MockBean
    private SolarPanelInstallationRepository solarPanelInstallationRepository;

    @MockBean
    private SolarPanelModelRepository solarPanelModelRepository;

    @MockBean
    private SolarPanelRepository solarPanelRepository;

    @MockBean
    private RestTemplate restTemplate;

    private SolarPanelModel solarPanelModel;
    private SolarPanel solarPanel;
    private MarketRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        solarPanelModel = new SolarPanelModel();
        solarPanelModel.setId(1);

        solarPanel = new SolarPanel();
        solarPanel.setId(1);
        solarPanel.setModel(solarPanelModel);

        requestDTO = new MarketRequestDTO();
        requestDTO.setCity("Camperdown");
        requestDTO.setRecyclingMethod("Recycle");
        requestDTO.setState("NSW");
        requestDTO.setRetirementDate("12/10/2028");
        requestDTO.setSilver(1);
        requestDTO.setAluminium(1);
        requestDTO.setSilicon(1);
        requestDTO.setCopper(1);
        requestDTO.setPolymers(1);
        requestDTO.setGlass(1);

    }

    @Test
    public void findPanelsByLocation_SUCCESS() {

    }
}