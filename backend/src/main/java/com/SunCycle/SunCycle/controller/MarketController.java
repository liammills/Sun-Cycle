package com.SunCycle.SunCycle.controller;

import com.SunCycle.SunCycle.dto.MarketRequestDTO;
import com.SunCycle.SunCycle.dto.MarketResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @PostMapping(value = "")
    public ResponseEntity<?> getUserQueryPanels(@RequestBody MarketRequestDTO dto) {
        return ResponseEntity.ok(marketService.searchUserQueryPanels(dto));
    }

}
