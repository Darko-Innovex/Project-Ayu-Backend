package lk.darkoinnovex.Ayu.controller;

import lk.darkoinnovex.Ayu.dto.HospitalDTO;
import lk.darkoinnovex.Ayu.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    // Find hospital by id
    @GetMapping("/hospital/{id}")
    public ResponseEntity<HospitalDTO> getDoctorById(@PathVariable Long id) {

        HospitalDTO dto = hospitalService.getHospitalById(id);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Find all hospital locations
    @GetMapping("/hospital/location")
    public ResponseEntity<List<String>> getAllHospitalsLocation() {

        List<String> locations = hospitalService.getAllHospitalsLocations();

        if (locations != null) {
            return ResponseEntity.status(HttpStatus.OK).body(locations);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
