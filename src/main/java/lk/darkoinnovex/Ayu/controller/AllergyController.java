package lk.darkoinnovex.Ayu.controller;

import java.util.List;

import lk.darkoinnovex.Ayu.dto.AllergyDTO;
import lk.darkoinnovex.Ayu.service.AllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
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

    // Save a new allergy
    @PostMapping("/allergies")
    public ResponseEntity<AllergyDTO> saveAllergy(@RequestBody AllergyDTO allergyDTO) {
        AllergyDTO allergy = allergyService.saveAllergy(allergyDTO);

        if (allergy != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(allergy);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Delete allergy
    @DeleteMapping("/allergies/{id}")
    public ResponseEntity<AllergyDTO> deleteAllergy(@PathVariable Long id) {
        AllergyDTO dto = allergyService.deleteAllergy(id);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
