package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.SolarPanelRequestDTO;
import com.SunCycle.SunCycle.dto.SolarPanelResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.SolarPanel;
import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.model.SolarPanelModel;
import com.SunCycle.SunCycle.repository.SolarPanelInstallationRepository;
import com.SunCycle.SunCycle.repository.SolarPanelModelRepository;
import com.SunCycle.SunCycle.repository.SolarPanelRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest
@ActiveProfiles("dev")
@Transactional
class SolarPanelServiceTest {
    @MockBean
    private SolarPanelModelRepository solarPanelModelRepository;

    @MockBean
    private SolarPanelInstallationRepository solarPanelInstallationRepository;

    @MockBean
    private SolarPanelRepository solarPanelRepository;

    @Autowired
    private SolarPanelService solarPanelService;

    private SolarPanel solarPanel;
    private SolarPanelModel solarPanelModel;
    private SolarPanelInstallation solarPanelInstallation;
    private SolarPanelRequestDTO requestDTO;

    @BeforeEach
    public void setUp() {
        solarPanel = new SolarPanel();
        solarPanelModel = new SolarPanelModel();
        solarPanelInstallation = new SolarPanelInstallation();
        requestDTO = new SolarPanelRequestDTO(4, 4, 4, "2023/10/14", "2023/10/14");

    }

    @Test
    public void createPanel_ModelNotFound() {
        when(solarPanelModelRepository.findById(anyInt())).thenReturn(Optional.empty());
        SolarPanelResponseDTO response = solarPanelService.createPanel(requestDTO);
        assertEquals(Status.NOT_FOUND, response.getStatus());
        assertEquals("Panel model not found", response.getMessage());
    }

    @Test
    public void createPanel_InstallationNotFound() {
        when(solarPanelModelRepository.findById(anyInt())).thenReturn(Optional.of(solarPanelModel));
        when(solarPanelInstallationRepository.findById(anyInt())).thenReturn(Optional.empty());
        SolarPanelResponseDTO response = solarPanelService.createPanel(requestDTO);
        assertEquals(Status.NOT_FOUND, response.getStatus());
        assertEquals("Installation not found", response.getMessage());
    }

    @Test
    public void createPanel_Success() {
        when(solarPanelModelRepository.findById(anyInt())).thenReturn(Optional.of(solarPanelModel));
        when(solarPanelInstallationRepository.findById(anyInt())).thenReturn(Optional.of(solarPanelInstallation));
        when(solarPanelRepository.save(any(SolarPanel.class))).thenReturn(solarPanel);

        SolarPanelResponseDTO response = solarPanelService.createPanel(requestDTO);

        assertEquals(Status.SUCCESS, response.getStatus());
        assertNotNull(response.getSolarPanel());
    }

    @Test
    public void updatePanel_Success() {
        // Prepare mocked request DTO
        SolarPanelRequestDTO mockRequest = new SolarPanelRequestDTO();
        mockRequest.setModelId(1); // assuming model ID
        mockRequest.setInstallationId(1); // assuming installation ID
        mockRequest.setInstallationDate("2023/10/14");
        mockRequest.setRetirementDate("2023/10/14");
//        mockRequest.setRecyclingMethod("recycle");


        // Mocking repository responses
        when(solarPanelRepository.findById(anyInt())).thenReturn(Optional.of(new SolarPanel()));
        when(solarPanelModelRepository.findById(anyInt())).thenReturn(Optional.of(new SolarPanelModel()));
        when(solarPanelInstallationRepository.findById(anyInt())).thenReturn(Optional.of(new SolarPanelInstallation()));
        when(solarPanelRepository.save(any(SolarPanel.class))).thenReturn(new SolarPanel());

        // Call the method under test
        SolarPanelResponseDTO response = solarPanelService.updatePanel(1, mockRequest); // assuming panel ID

        // Assertions
        assertNotNull(response);
        assertEquals(Status.SUCCESS, response.getStatus());
    }

    @Test
    public void updatePanel_PanelNotFound() {
        // Mocking repository response
        when(solarPanelRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Call the method under test
        SolarPanelResponseDTO response = solarPanelService.updatePanel(1, new SolarPanelRequestDTO()); // assuming panel ID

        // Assertions
        assertNotNull(response);
        assertEquals(Status.NOT_FOUND, response.getStatus());
        assertEquals("Panel not found", response.getMessage());
    }

    @Test
    public void updatePanel_ModelNotFound() {
        // Prepare mocked request DTO
        SolarPanelRequestDTO mockRequest = new SolarPanelRequestDTO();
        mockRequest.setModelId(1); // assuming model ID
        mockRequest.setInstallationId(1); // assuming installation ID
        mockRequest.setInstallationDate("2023/10/14");
        mockRequest.setRetirementDate("2023/10/14");
//        mockRequest.setRecyclingMethod("recycle");

        // Mocking repository responses
        when(solarPanelRepository.findById(anyInt())).thenReturn(Optional.of(new SolarPanel()));
        when(solarPanelModelRepository.findById(anyInt())).thenReturn(Optional.empty()); // Model not found

        // Call the method under test
        SolarPanelResponseDTO response = solarPanelService.updatePanel(1, mockRequest); // assuming panel ID

        // Assertions
        assertNotNull(response);
        assertEquals(Status.NOT_FOUND, response.getStatus());
        assertEquals("Model not found", response.getMessage());
    }

    @Test
    public void updatePanel_InstallationNotFound() {
        // Prepare mocked request DTO
        SolarPanelRequestDTO mockRequest = new SolarPanelRequestDTO();
        mockRequest.setModelId(1); // assuming model ID
        mockRequest.setInstallationId(1); // assuming installation ID
        mockRequest.setInstallationDate("2023/10/14");
        mockRequest.setRetirementDate("2023/10/14");
//        mockRequest.setRecyclingMethod("recycle");

        // Mocking repository responses
        when(solarPanelRepository.findById(anyInt())).thenReturn(Optional.of(new SolarPanel()));
        when(solarPanelModelRepository.findById(anyInt())).thenReturn(Optional.of(new SolarPanelModel()));
        when(solarPanelInstallationRepository.findById(anyInt())).thenReturn(Optional.empty()); // Installation not found

        // Call the method under test
        SolarPanelResponseDTO response = solarPanelService.updatePanel(1, mockRequest); // assuming panel ID

        // Assertions
        assertNotNull(response);
        assertEquals(Status.NOT_FOUND, response.getStatus());
        assertEquals("Installation not found", response.getMessage());
    }


    @Test
    public void deletePanel_Success() {
        // Mocking repository response
        SolarPanel mockPanel = new SolarPanel();
        mockPanel.setId(1); // assuming panel ID
        when(solarPanelRepository.findById(anyInt())).thenReturn(Optional.of(mockPanel));

        // Call the method under test
        SolarPanelResponseDTO response = solarPanelService.deletePanel(1); // assuming panel ID

        // Assertions
        assertNotNull(response);
        assertEquals(Status.SUCCESS, response.getStatus());
        assertEquals("Panel deleted successfully", response.getMessage());
    }

    @Test
    public void deletePanel_PanelNotFound() {
        // Mocking repository response
        when(solarPanelRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Call the method under test
        SolarPanelResponseDTO response = solarPanelService.deletePanel(1); // assuming panel ID

        // Assertions
        assertNotNull(response);
        assertEquals(Status.NOT_FOUND, response.getStatus());
        assertEquals("Panel not found", response.getMessage());
    }

}