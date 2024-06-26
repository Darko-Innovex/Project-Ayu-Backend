package lk.darkoinnovex.Ayu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lk.darkoinnovex.Ayu.dto.PatientDTO;
import lk.darkoinnovex.Ayu.service.PatientService;

@RestController
public class PatientController {
    
    @Autowired
    private PatientService patientService;

    // Find patient by id
    @GetMapping("/patient/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patient = patientService.getPatientById(id);

        if (patient != null) {
            return ResponseEntity.status(200).body(patient);
        } else {
            return ResponseEntity.status(404).body(null);
        }
        
    }

    // Update patient
    @PutMapping("/patient/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO dto = patientService.updatePatient(patientDTO);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
