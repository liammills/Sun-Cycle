package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.LoginResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("dev")
class AuthenticationServiceTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void registerUser_SUCCESS(){
        // Given
        String username = "test@example.com";
        String password = "password";


        LoginResponseDTO response = authenticationService.registerUser(username, password);

        // Then
        assertEquals(Status.SUCCESS, response.getStatus());
        assertNotNull(response.getJwt());
        assertEquals(username, response.getUser().getUsername());
        assertEquals(username, response.getUser().getEmail());

    }

    @Test
    public void registerUser_EXIST() {
        // Given
        String username = "test@example.com";
        String password = "password";


        LoginResponseDTO response_success = authenticationService.registerUser(username, password);
        assertEquals(Status.SUCCESS, response_success.getStatus());

        LoginResponseDTO response_exist = authenticationService.registerUser(username, password);
        assertEquals(Status.ALREADY_EXISTS, response_exist.getStatus());
        assertNull(response_exist.getJwt());
        assertNull(response_exist.getUser());

    }

    @Test
    public void loginUser_SUCCESS() {
        String username = "test@example.com";
        String password = "password";

        LoginResponseDTO register_response = authenticationService.registerUser(username, password);
        assertEquals(Status.SUCCESS, register_response.getStatus());

        LoginResponseDTO login_response = authenticationService.loginUser(username, password);
        assertEquals(Status.SUCCESS, login_response.getStatus());
        assertEquals(username, login_response.getUser().getEmail());
        assertEquals(username, login_response.getUser().getUsername());
    }

    @Test
    public void login_NotFound() {
        String username = "test@example.com";
        String password = "password";

        LoginResponseDTO login_response = authenticationService.loginUser(username, password);
        assertEquals(Status.NOT_FOUND, login_response.getStatus());
        assertNull(login_response.getUser());
        assertNull(login_response.getJwt());
    }

    @Test
    public void login_Failed() {
        String username = "test@example.com";
        String password = "password";

        LoginResponseDTO register_response = authenticationService.registerUser(username, password);
        assertEquals(Status.SUCCESS, register_response.getStatus());

        String wrongPassword = "passwor";
        LoginResponseDTO login_response = authenticationService.loginUser(username, wrongPassword);
        assertEquals(Status.ERROR, login_response.getStatus());
        assertNull(login_response.getUser());
        assertNull(login_response.getJwt());
    }

}