package com.SunCycle.SunCycle.controller;

import com.SunCycle.SunCycle.dto.SolarPanelDTO;
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
        return ResponseEntity.ok(solarPanelService.createPanel(dto));
    }

//    @PutMapping("/{panelId}/update")
//    public ResponseEntity<?> updatePanel(@PathVariable int panelId, @RequestBody SolarPanel panel) {
//        // Assuming panelId will be used to update a specific panel and details will be from the SolarPanel object.
//        return ResponseEntity.ok(solarPanelService.updatePanel(panelId, panel));
//    }
//
//    @DeleteMapping("/{panelId}/delete")
//    public ResponseEntity<?> deletePanel(@PathVariable int panelId) {
//        return ResponseEntity.ok(solarPanelService.deletePanel(panelId));
//    }

}
