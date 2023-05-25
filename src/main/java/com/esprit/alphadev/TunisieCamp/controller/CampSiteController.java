package com.esprit.alphadev.TunisieCamp.controller;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.service.CampSiteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/campsites")

public class CampSiteController {

    CampSiteService campSiteService;
    @PostMapping("/{campingCenterId}/campsites")
    public ResponseEntity<String> addCampsiteToCampingCenter(@PathVariable Long campingCenterId, @RequestBody CampSite campsite) {
        try {
            campSiteService.addCampsiteToCampingCenter(campingCenterId, campsite);
            return ResponseEntity.ok("Campsite added and assigned to camping center successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
   @GetMapping("/{campingCenterId}/campsites")
    public ResponseEntity<List<CampSite>> getCampsitesByCampingCenter(@PathVariable Long campingCenterId) {
        List<CampSite> campsites = campSiteService.getCampsitesByCampingCenter(campingCenterId);
        return ResponseEntity.ok(campsites);
    }
}
