package com.SunCycle.SunCycle.controller;

import com.SunCycle.SunCycle.dto.LoginResponseDTO;
import com.SunCycle.SunCycle.dto.SimpleUserDTO;
import com.SunCycle.SunCycle.model.User;
import com.SunCycle.SunCycle.repository.UserRepository;
import com.SunCycle.SunCycle.service.AuthenticationService;
import com.SunCycle.SunCycle.service.UpdateUserInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AuthenticationControllerTest {
    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private UpdateUserInfoService updateUserInfoService;

    // other service mocks as necessary

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void createUser_Success() {
        // Mocking and stubbing
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        LoginResponseDTO responseDTO = new LoginResponseDTO(new User().toSimpleUserDTO(), "");
        when(authenticationService.registerUser(anyString(), anyString())).thenReturn(responseDTO);

        // Execute the method being tested
        ResponseEntity<?> response = authenticationController.createUser(new User());

        // Assertions and verifications
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // other necessary assertions
    }

    @Test
    void createUser_UserAlreadyExists() {
        // Mocking and stubbing
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User("test@gmail.com", "test")));

        // Execute the method being tested
        ResponseEntity<?> response = authenticationController.createUser(new User("test@gmail.com", "test"));

        // Assertions and verifications
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        // other necessary assertions
    }

    @Test
    public void login_Success(){
        User user = new User("test@gmail.com", "test");
        SimpleUserDTO userDTO = user.toSimpleUserDTO();
        LoginResponseDTO response = new LoginResponseDTO(userDTO, "");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(authenticationService.loginUser(anyString(), anyString())).thenReturn(response);

        LoginResponseDTO responseDTO = authenticationController.loginUser(user);
        assertNotNull(responseDTO);
        assertEquals(userDTO, responseDTO.getUser());

    }

}