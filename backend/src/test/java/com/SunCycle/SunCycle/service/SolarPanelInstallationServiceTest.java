package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.SolarPanelInstallationRequestDTO;
import com.SunCycle.SunCycle.dto.SolarPanelInstallationResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.SolarPanel;
import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.model.User;
import com.SunCycle.SunCycle.repository.SolarPanelInstallationRepository;
import com.SunCycle.SunCycle.repository.SolarPanelRepository;
import com.SunCycle.SunCycle.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class SolarPanelInstallationServiceTest {

    @InjectMocks
    private SolarPanelInstallationService solarPanelInstallationService;

    @Mock
    private SolarPanelInstallationRepository solarPanelInstallationRepository;

    @Mock
    private SolarPanelRepository solarPanelRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    private SolarPanelInstallationRequestDTO dto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(authentication.getName()).thenReturn("test@example.com");

        dto = new SolarPanelInstallationRequestDTO();
        dto.setUserId(1);
        dto.setAddress("mock");
        dto.setType("mock");
        dto.setPostcode(1);
        dto.setState("mock");
        dto.setGeoLocation("mock");
    }

    @Test
    public void createSolarPanelInstallation_UserNotFound() {


        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Execute service call
        SolarPanelInstallationResponseDTO response = solarPanelInstallationService.createSolarPanelInstallation(dto);

        // Verify and assert
        assertNotNull(response);
        assertEquals(Status.NOT_FOUND, response.getStatus());
        assertEquals("User not found", response.getMessage());
    }

    @Test
    public void createSolarPanelInstallation_Success() {


        User mockUser = new User();


        when(userRepository.findById(anyInt())).thenReturn(Optional.of(mockUser));
        when(solarPanelInstallationRepository.save(any(SolarPanelInstallation.class))).thenAnswer(i -> i.getArguments()[0]);

        // Execute service call
        SolarPanelInstallationResponseDTO response = solarPanelInstallationService.createSolarPanelInstallation(dto);

        // Verify and assert
        assertNotNull(response);
        assertEquals(Status.SUCCESS, response.getStatus());
        assertNotNull(response.getSolarPanelInstallation());

    }

    @Test
    public void updateSolarPanelInstallation_NotFound() {
        // Assuming panelId does not exist
        int nonExistentPanelId = 999;

        User mockUser = new User();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));

        when(solarPanelInstallationRepository.findById(nonExistentPanelId)).thenReturn(Optional.empty());

        SolarPanelInstallationResponseDTO response = solarPanelInstallationService.updateSolarPanelInstallation(authentication, nonExistentPanelId, dto);

        assertNotNull(response);
        assertEquals(Status.NOT_FOUND, response.getStatus());
        assertEquals("Installation not found", response.getMessage());
    }

    @Test
    public void updateSolarPanelInstallation_UserNotFound() {
        SolarPanelInstallation installation = new SolarPanelInstallation();
        User user = new User();
        user.setUserId(1);
        installation.setUser(user);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(solarPanelInstallationRepository.findById(anyInt())).thenReturn(Optional.of(installation));

        SolarPanelInstallationResponseDTO response = solarPanelInstallationService.updateSolarPanelInstallation(authentication, anyInt(), dto);

        assertEquals(Status.NOT_FOUND, response.getStatus());
        assertEquals("Invalid user id", response.getMessage());

    }

    @Test
    public void updateSolarPanelInstallation_UserIdNotMatch(){
        SolarPanelInstallation installation = new SolarPanelInstallation();
        User user = new User();
        user.setUserId(2);
        installation.setUser(user);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(solarPanelInstallationRepository.findById(anyInt())).thenReturn(Optional.of(installation));

        SolarPanelInstallationResponseDTO response = solarPanelInstallationService.updateSolarPanelInstallation(authentication, anyInt(), dto);

        assertEquals(Status.ERROR, response.getStatus());
        assertEquals("Invalid user id", response.getMessage());
    }

    @Test
    public void updateSolarPanelInstallation_SUCCESS() {
        // Mocking objects and the behavior of the mocked objects
        SolarPanelInstallation oldInstallation = new SolarPanelInstallation();
        oldInstallation.setUser(new User());
        oldInstallation.getUser().setUserId(1); // the same ID as in dto


        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(oldInstallation.getUser()));
        when(solarPanelInstallationRepository.findById(anyInt())).thenReturn(Optional.of(oldInstallation));
        when(solarPanelInstallationRepository.save(any(SolarPanelInstallation.class))).thenReturn(oldInstallation);

        // Call the method to test
        SolarPanelInstallationResponseDTO response = solarPanelInstallationService.updateSolarPanelInstallation(authentication, 1, dto);

        // Assert the results (check if the method executed successfully)
        assertEquals(Status.SUCCESS, response.getStatus());
    }


    @Test
    public void deleteInstallation_Success() {
        int existingPanelId = 1; // Assuming this panel ID exists for this test case

        SolarPanelInstallation mockInstallation = new SolarPanelInstallation();
        mockInstallation.setId(existingPanelId);
        User mockUser = new User();
        mockUser.setUserId(1); // the same ID as in authentication
        mockInstallation.setUser(mockUser);

        SolarPanel panel = new SolarPanel();

        List<SolarPanel> panels = new ArrayList<>(); // assuming there are panels that need to be deleted
        panels.add(panel);

        when(solarPanelInstallationRepository.findById(existingPanelId)).thenReturn(Optional.of(mockInstallation));
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));
        when(solarPanelRepository.findSolarPanelsBySolarPanelInstallation(mockInstallation)).thenReturn(panels);

        SolarPanelInstallationResponseDTO response = solarPanelInstallationService.deleteInstallation(authentication, existingPanelId);

        assertNotNull(response);
        assertEquals(Status.SUCCESS, response.getStatus());
        verify(solarPanelRepository, times(1)).deleteAll(panels); // Ensure the related panels are deleted
        verify(solarPanelInstallationRepository, times(1)).delete(mockInstallation); // Ensure the installation is deleted
    }

    @Test
    public void deleteInstallation_InvalidPanelId() {
        Authentication auth = Mockito.mock(Authentication.class);
        when(solarPanelInstallationRepository.findById(anyInt())).thenReturn(Optional.empty());

        var response = solarPanelInstallationService.deleteInstallation(auth, 1);

        assertEquals(Status.NOT_FOUND, response.getStatus());
        assertEquals("Invalid panel id", response.getMessage());
    }

    @Test
    public void deleteInstallation_InvalidUserId_NotFound() {
        SolarPanelInstallation mockInstallation = new SolarPanelInstallation();
        User mockUser = new User();
        mockUser.setUserId(1);
        mockInstallation.setUser(mockUser);

        Authentication auth = Mockito.mock(Authentication.class);
        when(auth.getName()).thenReturn("test@example.com");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(solarPanelInstallationRepository.findById(anyInt())).thenReturn(Optional.of(mockInstallation));

        var response = solarPanelInstallationService.deleteInstallation(auth, 1);

        assertEquals(Status.NOT_FOUND, response.getStatus());
        assertEquals("Invalid user id", response.getMessage());
    }

    @Test
    public void deleteInstallation_InvalidUserId_Error() {
        SolarPanelInstallation mockInstallation = new SolarPanelInstallation();
        User mockUser = new User();
        mockUser.setUserId(1);
        mockInstallation.setUser(mockUser);

        User authUser = new User();
        authUser.setUserId(2);

        Authentication auth = Mockito.mock(Authentication.class);
        when(auth.getName()).thenReturn("test@example.com");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(authUser));
        when(solarPanelInstallationRepository.findById(anyInt())).thenReturn(Optional.of(mockInstallation));

        var response = solarPanelInstallationService.deleteInstallation(auth, 1);

        assertEquals(Status.ERROR, response.getStatus());
        assertEquals("Invalid user id", response.getMessage());
    }

    @Test
    public void getInstallationsByEmail_NotFound() {
        String nonExistentEmail = "nonexistent@example.com";

        when(userRepository.findByEmail(nonExistentEmail)).thenReturn(Optional.empty());

        List<SolarPanelInstallationResponseDTO> response = solarPanelInstallationService.getInstallationsByEmail(nonExistentEmail);

        assertNull(response);
    }

    @Test
    public void getInstallationsByEmail_Success() {
        String existingEmail = "exist@example.com";
        User mockUser = new User();
        // ... set user fields ...

        // TODO: fix this test case @Edward
        List<SolarPanelInstallation> mockInstallations = new ArrayList<>();
        // ... add mock installations ...

        when(userRepository.findByEmail(existingEmail)).thenReturn(Optional.of(mockUser));
        when(solarPanelInstallationRepository.findSolarPanelInstallationsByUser(mockUser)).thenReturn(mockInstallations);

        List<SolarPanelInstallationResponseDTO> response = solarPanelInstallationService.getInstallationsByEmail(existingEmail);

        assertEquals(mockInstallations, response);
    }

}