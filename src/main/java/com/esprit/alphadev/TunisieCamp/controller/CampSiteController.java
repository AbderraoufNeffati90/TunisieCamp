package com.esprit.alphadev.TunisieCamp.controller;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;
import com.esprit.alphadev.TunisieCamp.service.CampSiteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/campsites")

public class CampSiteController {

    @Autowired
    CampSiteService campSiteService;
    @PostMapping("/add-to-camping-center")
    public CampSite addCampsiteToCampingCenter(@RequestBody CampSite campsite, @RequestParam Integer idCentre) {
        return campSiteService.addCampsiteToCampingCenter(campsite, idCentre);
    }

    @GetMapping("/{name}")
    public List<CampSite> getCampsitesByCampingCenter(@PathVariable String name) {
        List<CampSite> campsites = campSiteService.getCampsitesByCampingCenter(name);

        return campsites;
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> updateCampsiteByName(@PathVariable String name, @RequestBody CampSite updatedCampsite) {
        campSiteService.updateCampsite(name, updatedCampsite);
        return ResponseEntity.ok("Campsite updated successfully");
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteCampsiteByName(@PathVariable String name) {
        campSiteService.deleteCampsiteByName(name);
        return ResponseEntity.ok("Campsite deleted successfully.");
    }
    @GetMapping("/search")
    public ResponseEntity<List<CampSite>> searchCampsites(@RequestParam String keyword) {
        List<CampSite> campsites = campSiteService.searchCampsites(keyword);
        return ResponseEntity.ok(campsites);
    }


}
