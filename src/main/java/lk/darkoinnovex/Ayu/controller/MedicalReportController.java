package lk.darkoinnovex.Ayu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lk.darkoinnovex.Ayu.entity.MedicalReport;

@RestController
public class MedicalReportController {

    //TODO: autowire medical report service layer
    
    // Return medical report of a specific appointment
    @GetMapping("/appointment/{id}/medical_report") 
    public ResponseEntity<MedicalReport> getMedicalReportOfAppointment(@PathVariable Long id) {
        return ResponseEntity.status(200).body(new MedicalReport());
    }

    // Return all medical reports of a specific patient
    @GetMapping("/patient/{id}/appointment/medical_report") 
    public ResponseEntity<List<MedicalReport>> getAllMedicalReportOfPatient(@PathVariable Long id) {
        return ResponseEntity.status(200).body(new ArrayList<MedicalReport>());
    }
}
