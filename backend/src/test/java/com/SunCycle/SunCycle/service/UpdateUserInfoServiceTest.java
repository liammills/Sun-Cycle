package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.LoginResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.User;
import com.SunCycle.SunCycle.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("dev")
@Transactional
class UpdateUserInfoServiceTest {

    @Autowired
    private UpdateUserInfoService updateUserInfoService;
    @Autowired
    private AuthenticationService authenticationService;

//    @MockBean
//    private UserRepository userRepository;

    private User user;


    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(1);
        user.setEmail("test@example.com");
        user.setPassword("password");
    }

    @Test
    void updateUserById_userNotFound() {
        // Arrange

        // Act
        LoginResponseDTO response = updateUserInfoService.updateUserById(999, "new@example.com", "newPassword");

        // Assert
        assertEquals(Status.NOT_FOUND, response.getStatus());
    }


    @Test
    public void updateUserById_success() {
        // Arrange
        String username = "test@example.com";
        String password = "password";

        LoginResponseDTO register_response = authenticationService.registerUser(username, password);
        assertEquals(Status.SUCCESS, register_response.getStatus());
        assertEquals("test@example.com", register_response.getUser().getUsername());

        LoginResponseDTO update_response = updateUserInfoService.updateUserById(register_response.getUser().getUserId(), "new@example.com", "new");
        assertEquals(Status.SUCCESS, update_response.getStatus());
        assertEquals("new@example.com", update_response.getUser().getUsername());

        LoginResponseDTO login_response = authenticationService.loginUser("new@example.com", "new");
        assertEquals(Status.SUCCESS, login_response.getStatus());
        assertEquals(login_response.getUser().getUsername(), "new@example.com");


    }


}