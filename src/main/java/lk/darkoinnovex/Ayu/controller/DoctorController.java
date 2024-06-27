package lk.darkoinnovex.Ayu.controller;

import java.util.ArrayList;
import java.util.List;

import lk.darkoinnovex.Ayu.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lk.darkoinnovex.Ayu.entity.Doctor;

@RestController
public class DoctorController {
    
    @Autowired
    private DoctorService doctorService;

    // Return all doctors that patient placed appointments
    @GetMapping("/patient/{id}/doctor") 
    public ResponseEntity<List<Doctor>> getAllDoctorsOfPatient(@PathVariable Long id) {
        return ResponseEntity.status(200).body(new ArrayList<Doctor>());
    }
}
