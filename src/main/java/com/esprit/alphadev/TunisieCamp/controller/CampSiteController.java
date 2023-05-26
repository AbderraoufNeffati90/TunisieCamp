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
    @PostMapping("/add-to-camping-center")
    public CampSite addCampsiteToCampingCenter(@RequestBody CampSite campsite, @RequestParam Long idCentre) {
        return campSiteService.addCampsiteToCampingCenter(campsite, idCentre);
    }

    @GetMapping("/{campingCenterName}/campsites")
    public List<CampSite> getCampsitesByCampingCenter(@PathVariable String name) {
        return campSiteService.getCampsitesByCampingCenter(name);
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> updateCampsiteByName(@PathVariable String name, @RequestBody CampSite updatedCampsite) {
        campSiteService.updateCampsite(name, updatedCampsite);
        return ResponseEntity.ok("Campsite updated successfully");
    }

    @DeleteMapping("/delete-by-name")
    public ResponseEntity<String> deleteCampsiteByName(@RequestParam String name) {
        campSiteService.deleteCampsiteByName(name);
        return ResponseEntity.ok("Campsite deleted successfully.");
    }



}
