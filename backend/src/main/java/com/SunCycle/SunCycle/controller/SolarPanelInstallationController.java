package com.SunCycle.SunCycle.controller;

import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.model.SolarPanelInstallationDTO;
import com.SunCycle.SunCycle.model.Status;
import com.SunCycle.SunCycle.service.SolarPanelInstallationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/panels")
public class SolarPanelInstallationController {

    @Autowired
    private SolarPanelInstallationService solarPanelInstallationService;

    private static final Logger Log = LoggerFactory.getLogger(SolarPanelInstallationController.class);

//    @GetMapping("/test")
//    public ResponseEntity<?> test(Authentication authentication){
//        System.out.println("authentication"+authentication.getName());
//        return ResponseEntity.ok("in the panels");
//    }

    @PostMapping("/create")
    public ResponseEntity<?> createInstallation(@RequestBody SolarPanelInstallation installation) {

        return ResponseEntity.ok(solarPanelInstallationService.createSolarPanelInstallation(installation.getUserId(),
                                                                                    installation.getGeoLocation(),
                                                                                    installation.getAddress(),
                                                                                    installation.getState(),
                                                                                    installation.getPostcode(),
                                                                                    installation.getType()));
    }

    @PutMapping("/{panelId}/update")
    public ResponseEntity<?> updateInstallation(Authentication authentication, @PathVariable int panelId, @RequestBody SolarPanelInstallation installation){
        SolarPanelInstallationDTO result = solarPanelInstallationService.updateSolarPanelInstallation(authentication, panelId, installation);
        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{panelId}/delete")
    public ResponseEntity<?> deleteInstalltion(Authentication authentication, @PathVariable int panelId) {
        SolarPanelInstallationDTO result = solarPanelInstallationService.deleteInstallation(authentication, panelId);
        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/")
    public ResponseEntity<?> getInstallations(Authentication authentication){
        return ResponseEntity.ok(solarPanelInstallationService.getInstallationsByEmail(authentication.getName()));
    }


}
