package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.MarketRequestDTO;
import com.SunCycle.SunCycle.dto.MarketResponseDTO;
import com.SunCycle.SunCycle.model.SolarPanel;
import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.model.SolarPanelModel;
import com.SunCycle.SunCycle.repository.SolarPanelInstallationRepository;
import com.SunCycle.SunCycle.repository.SolarPanelModelRepository;
import com.SunCycle.SunCycle.repository.SolarPanelRepository;
import com.SunCycle.SunCycle.utils.GetGeoLocation;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


class MarketServiceTest {

    @InjectMocks
    private MarketService marketService;

    @Mock
    private SolarPanelInstallationRepository solarPanelInstallationRepository;

    @Mock
    private SolarPanelModelRepository solarPanelModelRepository;

    @Mock
    private SolarPanelRepository solarPanelRepository;

    @Mock
    private GetGeoLocation getGeoLocation;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testSearchUserQueryPanels() {
//        // 1. Define test data
//        MarketRequestDTO dto = new MarketRequestDTO();
//        // ... set properties for dto ...
//
//        SolarPanelModel mockModel = new SolarPanelModel();
//        // ... set properties for mockModel ...
//
//        SolarPanel mockPanel = new SolarPanel();
//        // ... set properties for mockPanel ...
//
//        SolarPanelInstallation mockInstallation = new SolarPanelInstallation();
//        // ... set properties for mockInstallation ...
//
//        // 2. Mock external method calls
//        when(solarPanelModelRepository.findSolarPanelModelsByRecyclingMethodAndPolymersGreaterThanEqualAndSiliconGreaterThanEqualAndCopperGreaterThanEqualAndGlassGreaterThanEqualAndSilverGreaterThanEqualAndAluminiumGreaterThanEqual(anyString(), anyDouble(), anyDouble(), anyDouble(), anyDouble(), anyDouble(), anyDouble()))
//                .thenReturn(Arrays.asList(mockModel));
//
//        when(solarPanelRepository.findSolarPanelsBySolarPanelModel(any())).thenReturn(Arrays.asList(mockPanel));
//        when(solarPanelRepository.findSolarPanelsByRetirementDate(any())).thenReturn(Arrays.asList(mockPanel));
//        when(getGeoLocation.getLatAndLng(anyString())).thenReturn(new double[]{1.0, 1.0});
//        when(solarPanelInstallationRepository.findAll()).thenReturn(Arrays.asList(mockInstallation));
//
//        // 3. Call the method
//        List<MarketResponseDTO> result = marketService.searchUserQueryPanels(dto);
//
//        // 4. Validate the result
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        // ... other assertions based on expected behavior ...
//    }
}