package com.SunCycle.SunCycle.controller;

import com.SunCycle.SunCycle.dto.SolarPanelInstallationDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.service.SolarPanelInstallationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/installations")
public class SolarPanelInstallationController {

    @Autowired
    private SolarPanelInstallationService solarPanelInstallationService;

    @PostMapping("/create")
    public ResponseEntity<?> createInstallation(@RequestBody SolarPanelInstallationDTO dto) {
        SolarPanelInstallationDTO result = solarPanelInstallationService.createSolarPanelInstallation(dto);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{installationId}/update")
    public ResponseEntity<?> updateInstallation(Authentication authentication, @PathVariable int installationId, @RequestBody SolarPanelInstallationDTO dto) {
        SolarPanelInstallationDTO result = solarPanelInstallationService.updateSolarPanelInstallation(authentication, installationId, dto);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{installationId}/delete")
    public ResponseEntity<?> deleteInstallation(Authentication authentication, @PathVariable int installationId) {
        SolarPanelInstallationDTO result = solarPanelInstallationService.deleteInstallation(authentication, installationId);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/")
    public ResponseEntity<?> getInstallationsByUser(Authentication authentication) {
        List<SolarPanelInstallation> result = solarPanelInstallationService.getInstallationsByEmail(authentication.getName());

        if (result == null)
            return ResponseEntity.badRequest().body("This user doesn't have any associated solar panel installations.");

        return ResponseEntity.ok(solarPanelInstallationService.getInstallationsByEmail(authentication.getName()));
    }

}
