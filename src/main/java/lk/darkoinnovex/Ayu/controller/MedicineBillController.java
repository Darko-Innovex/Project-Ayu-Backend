package lk.darkoinnovex.Ayu.controller;

import java.util.List;

import lk.darkoinnovex.Ayu.dto.MedicineBillDTO;
import lk.darkoinnovex.Ayu.dto.MedicineDTO;
import lk.darkoinnovex.Ayu.service.MedicineBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
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
    public ResponseEntity<?> saveMedicineBill(@RequestBody MedicineBillDTO bill) {
        MedicineBillDTO medicineBillDTO = medicineBillService.saveMedicineBill(bill);

        if (medicineBillDTO != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(medicineBillDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Update medicine list day count
    @PutMapping("/medicine/{id}")
    public ResponseEntity<MedicineDTO> updateMedicine(@PathVariable Long id) {

        MedicineDTO medicineDTO = medicineBillService.updateMedicineDayCount(id);

        if (medicineDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(medicineDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/appointment/{appointmentId}/medicine_list")
    public ResponseEntity<MedicineBillDTO> getMedicineBillOfAppointment(@PathVariable Long appointmentId) {

        MedicineBillDTO dto = medicineBillService.findMedicineBillByAppointment(appointmentId);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
