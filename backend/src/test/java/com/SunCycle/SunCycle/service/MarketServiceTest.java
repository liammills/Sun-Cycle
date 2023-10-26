package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.MarketRequestDTO;
import com.SunCycle.SunCycle.model.SolarPanel;
import com.SunCycle.SunCycle.model.SolarPanelModel;
import com.SunCycle.SunCycle.repository.SolarPanelInstallationRepository;
import com.SunCycle.SunCycle.repository.SolarPanelModelRepository;
import com.SunCycle.SunCycle.repository.SolarPanelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//@TestPropertySource(locations="classpath:application-dev.properties")
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

//    @Test
//    public void testFindPanelsByModel() {
//
//        when(solarPanelModelRepository.findSolarPanelModelByModelNameAndRecyclingMethodAndPolymersAndSiliconAndCopperAndGlassAndSilverAndAluminium(
//                anyString(), anyString(), anyDouble(), anyDouble(), anyDouble(), anyDouble(), anyDouble(), anyDouble()
//        )).thenReturn(solarPanelModel);
//
//        when(solarPanelRepository.findSolarPanelsBySolarPanelModel(any())).thenReturn(Collections.singletonList(solarPanel));
//
//        List<SolarPanel> retrievedPanels = marketService.findPanelsByModel(requestDTO);
//
//        assertEquals(1, retrievedPanels.size());
//        assertEquals(solarPanel, retrievedPanels.get(0));
//    }

//    @Test
//    public void testFindPanelsByMethodAndDate(){
//        ArrayList<SolarPanel> list = new ArrayList<SolarPanel>();
//        list.add(solarPanel);
//        when(solarPanelRepository.findSolarPanelsByRecyclingMethodAndRetirementDate(anyString(), any(Date.class))).thenReturn(list);
//
//        List<SolarPanel> result = marketService.findPanelsByModel(requestDTO);
//        assertEquals(1, result.size());
//        assertEquals(solarPanel, result.get(0));
//    }
//
//    @Test
//    public void testSearchUserQueryPanels() {
//
//        SolarPanelInstallation solarPanelInstallation = new SolarPanelInstallation();
//        solarPanel.setInstallation(solarPanelInstallation);
//        solarPanel.setModel(solarPanelModel);
//        List<SolarPanel> solarPanelList = List.of(solarPanel);
//
//        when(solarPanelModelRepository.findSolarPanelModelByPolymersAndSiliconAndCopperAndGlassAndSilverAndAluminium(anyDouble(), anyDouble(), anyDouble(), anyDouble(), anyDouble(), anyDouble()))
//                .thenReturn(solarPanelModel);
//
//        when(solarPanelRepository.findSolarPanelsBySolarPanelModel(any(SolarPanelModel.class)))
//                .thenReturn(solarPanelList);
//
//        when(solarPanelRepository.findSolarPanelsByRecyclingMethodAndRetirementDate(anyString(), any(Date.class)))
//                .thenReturn(solarPanelList);
//
//        when(solarPanelRepository.findAll())
//                .thenReturn(solarPanelList);
//
//
//        MarketResponseDTO response = marketService.searchUserQueryPanels(requestDTO);
//
//
//        assertNotNull(response);
//        assertEquals(1, response.getResult().size());
//        verify(solarPanelRepository, times(1)).findSolarPanelsBySolarPanelModel(any(SolarPanelModel.class));
//
//
//    }
}