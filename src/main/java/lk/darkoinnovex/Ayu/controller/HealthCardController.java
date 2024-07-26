package lk.darkoinnovex.Ayu.controller;

import lk.darkoinnovex.Ayu.dto.HealthCardDTO;
import lk.darkoinnovex.Ayu.dto.PatientDTO;
import lk.darkoinnovex.Ayu.service.HealthCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class HealthCardController {

    @Autowired
    private HealthCardService healthCardService;

    @GetMapping("/health-card/config")
    public ResponseEntity<PatientDTO> getHealthCardConfig(@RequestParam("pinNo") Long pinNo, @RequestParam("password") short password) {
        PatientDTO patientDTO = healthCardService.findByPinAndPassword(pinNo, password);

        if (patientDTO != null) {
            return ResponseEntity.ok(patientDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/health-card")
    public ResponseEntity<String> saveHealthCard(@RequestBody HealthCardDTO dto) {
        HealthCardDTO saved = healthCardService.saveHealthCard(dto);

        if (saved != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Health Card Saved");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Health Card Save Failed");
        }
    }
}
