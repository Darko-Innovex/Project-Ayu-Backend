package lk.darkoinnovex.Ayu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    
    //TODO: autowire medical report service layer

    // Return all reviews of a specific doctor that placed by specific patient
    @GetMapping("/patient/{pId}/doctor/{dId}/reviews") 
    public ResponseEntity<List<?>> getAllReviewsOfDoctor(@PathVariable Long pId, @PathVariable Long dId) {
        return ResponseEntity.status(200).body(new ArrayList<>());
    }

    // Save a new review
    @PostMapping("/reviews")
    public ResponseEntity<?> saveReview(@RequestBody Long review) {
        return ResponseEntity.status(201).body(null);
    }

    // Find review by id
    @GetMapping("/reviews/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(null);
    }
}
