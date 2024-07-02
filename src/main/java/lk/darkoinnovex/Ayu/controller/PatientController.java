package lk.darkoinnovex.Ayu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lk.darkoinnovex.Ayu.dto.PatientDTO;
import lk.darkoinnovex.Ayu.service.PatientService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Save Patient
    @PostMapping("/patient")
    public ResponseEntity<PatientDTO> savePatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO patient = patientService.createPatient(patientDTO);

        if (patient != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(patient);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Find patient by id
    @GetMapping("/patient/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patient = patientService.getPatientById(id);

        if (patient != null) {
            return ResponseEntity.status(HttpStatus.OK).body(patient);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    // Update patient
    @PutMapping("/patient")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO dto = patientService.updatePatient(patientDTO);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/patient")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> dtos = patientService.getAllPatients();

        if (dtos != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //getPatientByHealthCardID
    @GetMapping("/card/{id}/patient")
    public ResponseEntity<Object> getPatientByHealthCardID(@PathVariable String id) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
