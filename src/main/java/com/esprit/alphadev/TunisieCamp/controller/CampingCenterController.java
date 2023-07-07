package com.esprit.alphadev.TunisieCamp.controller;

import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;
import com.esprit.alphadev.TunisieCamp.service.CampingCenterService;
import com.esprit.alphadev.TunisieCamp.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/camping-centers")

public class CampingCenterController {

    CampingCenterService campingCenterService;
    FeedbackService feedbackService;

    @GetMapping("/getall")
    public List<CampingCenter> getAllCampingCenters() {
        return campingCenterService.getAllCampingCenters();
    }

    @GetMapping("/get/{id}")
    public CampingCenter getCampingCenterById(@PathVariable Integer id) {
        return campingCenterService.getCampingCenterById(id);
    }

    @PostMapping
    public ResponseEntity<String> AddCampingCenter(
            @RequestParam("userId") Integer userId,
            @RequestBody CampingCenter campingCenter
                                                   ) {


        Long l = new Long(userId);
        campingCenterService.AddCampingCenter(campingCenter, l);
        return ResponseEntity.ok("CampingCenter added successfully.");
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
