package com.SunCycle.SunCycle.controller;

import com.SunCycle.SunCycle.service.MarketPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/marketplace")
public class MarketPlaceController {

    @Autowired
    private MarketPlaceService marketPlaceService;

    @PostMapping("/")
    public ResponseEntity<?> getUserQueryPanels(@RequestBody Map<String, ?> query) {
        return ResponseEntity.ok(marketPlaceService.searchUserQueryPanels(query));
    }

    @GetMapping("/")
    public ResponseEntity<?> getPopularPanels() {
        return ResponseEntity.ok(marketPlaceService.searchPopularPanels());
    }

}
