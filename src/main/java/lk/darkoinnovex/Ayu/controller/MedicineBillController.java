package lk.darkoinnovex.Ayu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lk.darkoinnovex.Ayu.entity.MedicineBill;
import lk.darkoinnovex.Ayu.entity.MedicineList;

@RestController
public class MedicineBillController {

    //TODO: autowire medical report service layer
    
    // Return medicine bill of a specific appointment
    @GetMapping("/appointment/{id}/medicine_bill") 
    public ResponseEntity<MedicineBill> getMedicalBillOfAppointment(@PathVariable Long id) {
        return ResponseEntity.status(200).body(new MedicineBill());
    }

    // Return currently using drug list of a specific patient
    @GetMapping("/patient/{id}/drug_list")
    public ResponseEntity<List<MedicineList>> getCurrentDrugListOfPatient(@PathVariable Long id) {
        return ResponseEntity.status(200).body(new ArrayList<MedicineList>());
    }
}
