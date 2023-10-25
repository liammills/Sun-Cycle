package com.SunCycle.SunCycle.controller;

import com.SunCycle.SunCycle.dto.SolarPanelModelRequestDTO;
import com.SunCycle.SunCycle.dto.SolarPanelModelResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.SolarPanelModel;
import com.SunCycle.SunCycle.service.SolarPanelModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
@CrossOrigin(origins = "http://localhost:3000")
public class SolarPanelModelController {

    @Autowired
    private SolarPanelModelService solarPanelModelService;

    @GetMapping("")
    public ResponseEntity<?> getAllModels() {
        List<SolarPanelModel> result = solarPanelModelService.getAllModels();

        if (result.isEmpty())
            return ResponseEntity.badRequest().body("There's no model available");

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{modelId}")
    public ResponseEntity<?> getModelById(@PathVariable int modelId) {
        SolarPanelModelResponseDTO result = solarPanelModelService.getModelById(modelId);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<?> createModel(@RequestBody SolarPanelModelRequestDTO dto) {
        SolarPanelModelResponseDTO result = solarPanelModelService.createModel(dto);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{modelId}")
    public ResponseEntity<?> updateModel(@PathVariable int modelId, @RequestBody SolarPanelModelRequestDTO dto) {
        SolarPanelModelResponseDTO result = solarPanelModelService.updateModel(modelId, dto);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{modelId}")
    public ResponseEntity<?> deleteModel(@PathVariable int modelId) {
        SolarPanelModelResponseDTO result = solarPanelModelService.deleteModel(modelId);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

}
