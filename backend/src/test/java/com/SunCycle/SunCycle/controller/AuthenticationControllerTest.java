package com.SunCycle.SunCycle.controller;


import com.SunCycle.SunCycle.dto.LoginResponseDTO;
import com.SunCycle.SunCycle.dto.SimpleUserDTO;
import com.SunCycle.SunCycle.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ActiveProfiles("dev")
@Transactional
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    // other service mocks as necessary

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void createUser_Success() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.user.email", is("test@example.com")))
                        .andExpect(jsonPath("$.user.username", is("test@example.com")))
                        .andExpect(jsonPath("$.jwt", notNullValue()))
                        .andExpect(jsonPath("$.status", is("SUCCESS")));
    }

    @Test
    void createUser_UserAlreadyExists() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

//        create a new user
        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Email already exist"));
    }

    @Test
    public void login_Success() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

//        create a new user
        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.email", is("test@example.com")))
                .andExpect(jsonPath("$.user.username", is("test@example.com")))
                .andExpect(jsonPath("$.jwt", notNullValue()))
                .andExpect(jsonPath("$.status", is("SUCCESS")));

    }

    @Test
    public void login_FAILED() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

//        create a new user
        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        User failed = new User();
        failed.setEmail("test@example.com");
        failed.setPassword("passwor");
        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(failed)))
                .andExpect(status().is4xxClientError())
                .andExpect(status().is(401))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Authentication failed"));
    }

}