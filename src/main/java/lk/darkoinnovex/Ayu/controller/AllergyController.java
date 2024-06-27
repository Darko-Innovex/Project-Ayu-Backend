package lk.darkoinnovex.Ayu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lk.darkoinnovex.Ayu.entity.Allergy;
import lk.darkoinnovex.Ayu.service.PatientService;

@RestController
public class AllergyController {

    @Autowired
    private PatientService patientService;
    
    // Return all allergies of a specific patient
    @GetMapping("/patient/{id}/allergies")
    public ResponseEntity<List<Allergy>> getAllAllergiesOfPatient(@PathVariable Long id) {
        return ResponseEntity.status(200).body(new ArrayList<Allergy>());
    }
}
