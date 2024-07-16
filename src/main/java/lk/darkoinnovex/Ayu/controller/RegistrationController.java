package lk.darkoinnovex.Ayu.controller;

import lk.darkoinnovex.Ayu.dto.PatientDTO;
import lk.darkoinnovex.Ayu.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class RegistrationController {

    @Autowired
    private PatientService patientService;

    // Register new Patient
    @PostMapping("/auth/sign-up")
    public ResponseEntity<String> SignUpConfig(@RequestBody PatientDTO dto) {
        System.out.println(dto);
        PatientDTO patient = patientService.createPatient(dto);

        if (patient != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Patient created");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient not created");
        }

    }
}
