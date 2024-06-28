package lk.darkoinnovex.Ayu.controller;

import java.util.List;

import lk.darkoinnovex.Ayu.dto.AllergyDTO;
import lk.darkoinnovex.Ayu.service.AllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllergyController {

    @Autowired
    private AllergyService allergyService;
    
    // Return all allergies of a specific patient
    @GetMapping("/patient/{id}/allergies")
    public ResponseEntity<List<AllergyDTO>> getAllAllergiesOfPatient(@PathVariable Long id) {

        List<AllergyDTO> dtos = allergyService.getAllergyByPatientId(id);

        if (dtos != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
