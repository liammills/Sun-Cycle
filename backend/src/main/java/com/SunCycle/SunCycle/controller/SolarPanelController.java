package com.SunCycle.SunCycle.controller;

import com.SunCycle.SunCycle.dto.SolarPanelDTO;
import com.SunCycle.SunCycle.dto.SolarPanelInstallationDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.*;
import com.SunCycle.SunCycle.service.SolarPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/panels")
public class SolarPanelController {

    @Autowired
    private SolarPanelService solarPanelService;

    @PostMapping("/create")
    public ResponseEntity<?> createPanel(@RequestBody SolarPanelDTO dto) {
        SolarPanelDTO result = solarPanelService.createPanel(dto);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{panelId}/update")
    public ResponseEntity<?> updatePanel(@PathVariable int panelId, @RequestBody SolarPanelDTO dto) {
        SolarPanelDTO result = solarPanelService.updatePanel(panelId, dto);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{panelId}/delete")
    public ResponseEntity<?> deletePanel(@PathVariable int panelId) {
        SolarPanelDTO result = solarPanelService.deletePanel(panelId);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

}
