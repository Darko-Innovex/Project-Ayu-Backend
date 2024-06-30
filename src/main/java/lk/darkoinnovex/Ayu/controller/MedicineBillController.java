package lk.darkoinnovex.Ayu.controller;

import java.util.List;

import lk.darkoinnovex.Ayu.dto.MedicineBillDTO;
import lk.darkoinnovex.Ayu.dto.MedicineDTO;
import lk.darkoinnovex.Ayu.service.MedicineBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicineBillController {

    @Autowired
    private MedicineBillService medicineBillService;

    // Return currently using drug list of a specific patient
    @GetMapping("/patient/{id}/drug_list")
    public ResponseEntity<List<MedicineDTO>> getCurrentDrugListOfPatient(@PathVariable Long id) {
        List<MedicineDTO> dtos = medicineBillService.getCurrentMedicineListOfPatient(id);

        if (dtos != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Save a new medicine bill
    @PostMapping("/medicine_bill")
    public ResponseEntity<?> saveMedicineBill(@PathVariable Long bill) {
        return ResponseEntity.status(201).body(null);
    }
}
