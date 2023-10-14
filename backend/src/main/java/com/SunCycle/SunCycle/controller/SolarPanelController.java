package com.SunCycle.SunCycle.controller;

import com.SunCycle.SunCycle.dto.SolarPanelRequestDTO;
import com.SunCycle.SunCycle.dto.SolarPanelResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
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
    public ResponseEntity<?> createPanel(@RequestBody SolarPanelRequestDTO dto) {
        SolarPanelResponseDTO result = solarPanelService.createPanel(dto);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{panelId}/update")
    public ResponseEntity<?> updatePanel(@PathVariable int panelId, @RequestBody SolarPanelRequestDTO dto) {
        SolarPanelResponseDTO result = solarPanelService.updatePanel(panelId, dto);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{panelId}/delete")
    public ResponseEntity<?> deletePanel(@PathVariable int panelId) {
        SolarPanelResponseDTO result = solarPanelService.deletePanel(panelId);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

}
