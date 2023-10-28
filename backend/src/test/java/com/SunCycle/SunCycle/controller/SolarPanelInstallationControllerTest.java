package com.SunCycle.SunCycle.controller;

import com.SunCycle.SunCycle.dto.SolarPanelInstallationRequestDTO;
import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("dev")
@Transactional
@AutoConfigureMockMvc
class SolarPanelInstallationControllerTest {

    @Autowired
    private SolarPanelInstallationController solarPanelInstallationController;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("installationTest@example.com")
    public void createAndGetInstallation_SUCCESS() throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        User user = (User) userDetails;
        String username = user.getUsername();
        System.out.println(user.getUserId());

        SolarPanelInstallationRequestDTO request = new SolarPanelInstallationRequestDTO();
        request.setUserId(user.getUserId());
        request.setPostcode(2008);
        request.setType("Business");
        request.setAddress("1 park lane");
        request.setState("NSW");


        mockMvc.perform(get("/installations")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        mockMvc.perform(post("/installations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.solarPanelInstallation.email", is(user.getEmail())))
                .andExpect(jsonPath("$.solarPanelInstallation.address", is(request.getAddress())))
                .andExpect(jsonPath("$.solarPanelInstallation.state", is(request.getState())))
                .andExpect(jsonPath("$.solarPanelInstallation.postcode", is(request.getPostcode())))
                .andExpect(jsonPath("$.solarPanelInstallation.type", is(request.getType())));


    }

    @Test
    @WithUserDetails("installationTest@example.com")
    public void updateInstallation_SUCCESS() throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        User user = (User) userDetails;
        String username = user.getUsername();

        SolarPanelInstallationRequestDTO createRequest = new SolarPanelInstallationRequestDTO();
        createRequest.setUserId(user.getUserId());
        createRequest.setPostcode(2008);
        createRequest.setType("Business");
        createRequest.setAddress("1 park lane");
        createRequest.setState("NSW");

        MvcResult mvcResult = mockMvc.perform(post("/installations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.solarPanelInstallation.email", is(user.getEmail())))
                        .andExpect(jsonPath("$.solarPanelInstallation.address", is(createRequest.getAddress())))
                        .andExpect(jsonPath("$.solarPanelInstallation.state", is(createRequest.getState())))
                        .andExpect(jsonPath("$.solarPanelInstallation.postcode", is(createRequest.getPostcode())))
                        .andExpect(jsonPath("$.solarPanelInstallation.type", is(createRequest.getType())))
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        JsonNode responseJson = objectMapper.readTree(responseBody);
        int installationId = responseJson.path("solarPanelInstallation").path("id").asInt();

        SolarPanelInstallationRequestDTO updateRequest = new SolarPanelInstallationRequestDTO();
        updateRequest.setState("NSW");
        updateRequest.setAddress("49 Regent St, Chippendale");
        updateRequest.setType("Business");
        updateRequest.setPostcode(2008);
        updateRequest.setUserId(user.getUserId());

        mockMvc.perform(put("/installations/" + installationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.solarPanelInstallation.email", is(username)))
                .andExpect(jsonPath("$.solarPanelInstallation.address", is(updateRequest.getAddress())))
                .andExpect(jsonPath("$.solarPanelInstallation.state", is(updateRequest.getState())))
                .andExpect(jsonPath("$.solarPanelInstallation.postcode", is(updateRequest.getPostcode())))
                .andExpect(jsonPath("$.solarPanelInstallation.type", is(updateRequest.getType())))
                .andExpect(jsonPath("$.status", is("SUCCESS")));
    }

    @Test
    @WithUserDetails("installationTest@example.com")
    public void deleteInstallation_SUCCESS() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        User user = (User) userDetails;
        String username = user.getUsername();

        SolarPanelInstallationRequestDTO createRequest = new SolarPanelInstallationRequestDTO();
        createRequest.setUserId(user.getUserId());
        createRequest.setPostcode(2008);
        createRequest.setType("Business");
        createRequest.setAddress("1 park lane");
        createRequest.setState("NSW");

        MvcResult mvcResult = mockMvc.perform(post("/installations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.solarPanelInstallation.email", is(user.getEmail())))
                .andExpect(jsonPath("$.solarPanelInstallation.address", is(createRequest.getAddress())))
                .andExpect(jsonPath("$.solarPanelInstallation.state", is(createRequest.getState())))
                .andExpect(jsonPath("$.solarPanelInstallation.postcode", is(createRequest.getPostcode())))
                .andExpect(jsonPath("$.solarPanelInstallation.type", is(createRequest.getType())))
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        JsonNode responseJson = objectMapper.readTree(responseBody);
        int installationId = responseJson.path("solarPanelInstallation").path("id").asInt();

        mockMvc.perform(delete("/installations/" + installationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.solarPanelInstallation.email", is(username)))
                .andExpect(jsonPath("$.solarPanelInstallation.address", is(createRequest.getAddress())))
                .andExpect(jsonPath("$.solarPanelInstallation.state", is(createRequest.getState())))
                .andExpect(jsonPath("$.solarPanelInstallation.postcode", is(createRequest.getPostcode())))
                .andExpect(jsonPath("$.solarPanelInstallation.type", is(createRequest.getType())))
                .andExpect(jsonPath("$.status", is("SUCCESS")));
    }
}