package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.SolarPanelRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

class SolarPanelServiceTest {

    @Test
    @WithMockUser(username = "user")
    void createPanelModelExistsInstallationExists() {
        SolarPanelRequestDTO dto = new SolarPanelRequestDTO(1, 4, "2023/10/14", "2028/10/14", "Recycle");

    }

    @Test
    void updatePanel() {
    }

    @Test
    void deletePanel() {
    }
}