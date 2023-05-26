package com.esprit.alphadev.TunisieCamp.controller;

import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;
import com.esprit.alphadev.TunisieCamp.service.CampingCenterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/camping-centers")

public class CampingCenterController {

    CampingCenterService campingCenterService;

    @GetMapping("/getall")
    public List<CampingCenter> getAllCampingCenters() {
        return campingCenterService.getAllCampingCenters();
    }

    @GetMapping("/get/{id}")
    public CampingCenter getCampingCenterById(@PathVariable Long id) {
        return campingCenterService.getCampingCenterById(id);
    }
    @PostMapping("/add")
    public ResponseEntity<String> AddCampingCenter(@RequestBody CampingCenter campingCenter) {
        campingCenterService.AddCampingCenter(campingCenter);
        return ResponseEntity.ok("Camping Center added successfully.");
    }


    @PutMapping("/update/{name}")
    public ResponseEntity<String> updateCampingCenter(@PathVariable String name, @RequestBody CampingCenter campingCenter) {
        campingCenterService.updateCampingCenter(name, campingCenter);
        return ResponseEntity.ok("Camping Center updated successfully.");
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteCampingCenter(@PathVariable String name) {
        campingCenterService.deleteCampingCenter(name);
        return ResponseEntity.ok("Camping Center deleted successfully.");
    }

    @GetMapping("/search")
    public ResponseEntity<List<CampingCenter>> searchCampingCenters(@RequestParam String keyword) {
        List<CampingCenter> campingCenters = campingCenterService.searchCampingCenters(keyword);
        return ResponseEntity.ok(campingCenters);
    }
}
