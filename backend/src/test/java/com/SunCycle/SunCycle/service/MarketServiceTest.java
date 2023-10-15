package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.MarketRequestDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.SolarPanel;
import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.model.SolarPanelModel;
import com.SunCycle.SunCycle.repository.SolarPanelInstallationRepository;
import com.SunCycle.SunCycle.repository.SolarPanelModelRepository;
import com.SunCycle.SunCycle.repository.SolarPanelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

class MarketPlaceServiceTest {

    @Mock
    private SolarPanelModelRepository solarPanelModelRepository;

    @Mock
    private SolarPanelRepository solarPanelRepository;

    @Mock
    private SolarPanelInstallationRepository solarPanelInstallationRepository;

    @InjectMocks
    private MarketService marketPlaceService;

    private MarketRequestDTO requestDTO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        requestDTO = new MarketRequestDTO(); // Set properties based on your criteria
        requestDTO.setRecyclingMethod("");
        requestDTO.setRetirementDate("12/10/2028");
        requestDTO.setCity("");
        requestDTO.setState("");
        requestDTO.setPolymers(1);
        requestDTO.setGlass(1);
        requestDTO.setCopper(1);
        requestDTO.setSilicon(1);
        requestDTO.setSilver(1);
        requestDTO.setAluminium(1);
    }

    @Test
    public void testSearchUserQueryPanels_Success() {
        // Prepare data
        SolarPanelModel mockModel = new SolarPanelModel(); // Set properties if necessary
        SolarPanel mockPanel = new SolarPanel(); // Set properties if necessary
        mockPanel.setModel(mockModel);
        SolarPanelInstallation mockInstallation = new SolarPanelInstallation(); // Set properties if necessary
        mockPanel.setInstallation(mockInstallation);

        when(solarPanelModelRepository.findSolarPanelModelByPolymersAndSiliconAndCopperAndGlassAndSilverAndAluminium(anyDouble(), anyDouble(), anyDouble(), anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(mockModel);
        when(solarPanelRepository.findSolarPanelsBySolarPanelModel(mockModel)).thenReturn(Collections.singletonList(mockPanel));

        // Execute service method


        var response = marketPlaceService.searchUserQueryPanels(requestDTO);

        // Assertions
        assertEquals(Status.SUCCESS, response.getStatus());
        assertNotNull(response.getResult());
        assertFalse(response.getResult().isEmpty()); // Ensure result map is not empty
    }

    @Test
    public void testSearchUserQueryPanels_ModelNotFound() {
        // If model not found
        when(solarPanelModelRepository.findSolarPanelModelByPolymersAndSiliconAndCopperAndGlassAndSilverAndAluminium(anyDouble(), anyDouble(), anyDouble(), anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(null);

        // Execute service method
        var response = marketPlaceService.searchUserQueryPanels(requestDTO);

        // Assertions
        assertNotNull(response);
    }
}