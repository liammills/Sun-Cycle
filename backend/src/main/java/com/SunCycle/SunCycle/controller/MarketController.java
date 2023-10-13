package com.SunCycle.SunCycle.controller;

import com.SunCycle.SunCycle.dto.MarketRequestDTO;
import com.SunCycle.SunCycle.dto.MarketResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.service.MarketPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketPlaceService marketPlaceService;

    @PostMapping(value = "/")
    public ResponseEntity<?> getUserQueryPanels(@RequestBody MarketRequestDTO dto) {
        MarketResponseDTO result = marketPlaceService.searchUserQueryPanels(dto);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/")
    public ResponseEntity<?> getPopularPanels() {
        MarketResponseDTO result = marketPlaceService.searchPopularPanels();

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

}
