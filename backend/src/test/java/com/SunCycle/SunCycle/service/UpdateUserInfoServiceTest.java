package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.LoginResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.User;
import com.SunCycle.SunCycle.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserInfoServiceTest {

    @InjectMocks
    private UpdateUserInfoService updateUserInfoService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

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
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());

        // Act
        LoginResponseDTO response = updateUserInfoService.updateUserById(user.getUserId(), "new@example.com", "newPassword");

        // Assert
        assertEquals(Status.NOT_FOUND, response.getStatus());
    }

//    @Test
//    void updateUserById_emailAlreadyUsed() {
//        // Arrange
//        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
//        when(userRepository.findByEmail("new@example.com")).thenReturn(Optional.of(new User()));
//
//        // Act
//        LoginResponseDTO response = updateUserInfoService.updateUserById(user.getUserId(), "new@example.com", "newPassword");
//
//        // Assert
//        assertEquals("this email has aldready been used", response.getMessage());
//    }

//    @Test
//    void updateUserById_success() {
//        // Arrange
//        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
////        when(userRepository.findByEmail("new@example.com")).thenReturn(Optional.empty());
//        when(passwordEncoder.encode("newPassword")).thenReturn("encodedPassword");
//        when(authenticationManager.authenticate(any())).thenReturn(mock(Authentication.class));
//        when(tokenService.generateJwt(any())).thenReturn("token");
//
//
//        // Act
//        LoginResponseDTO response = updateUserInfoService.updateUserById(user.getUserId(), "new@example.com", "newPassword");
//
//        // Assert
//        assertEquals("token", response.getJwt());
//        assertEquals("new@example.com", response.getUser().getEmail());
//        assertEquals("new@example.com", response.getUser().getUsername());
//        assertEquals(user.getUserId(), response.getUser().getUserId());
//
//    }

    @Test
    void updateUserById_authenticationFailed() {
        // Arrange
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
//        when(userRepository.findByEmail("new@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedPassword");
        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Mock exception"));

        // Act
        LoginResponseDTO response = updateUserInfoService.updateUserById(user.getUserId(), "new@example.com", "newPassword");

        // Assert
        assertEquals(Status.ERROR, response.getStatus());
    }

}