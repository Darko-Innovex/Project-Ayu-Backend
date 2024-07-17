package lk.darkoinnovex.Ayu.controller;

import lk.darkoinnovex.Ayu.dto.MedicalReportDTO;
import lk.darkoinnovex.Ayu.service.MedicalReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class MedicalReportController {

    @Autowired
    private MedicalReportService medicalReportService;

    // Return medical report of a specific appointment
    @GetMapping("/appointment/{id}/medical_report")
    public ResponseEntity<MedicalReportDTO> getMedicalReportOfAppointment(@PathVariable Long id) {
        MedicalReportDTO report = medicalReportService.getReportByAppoinmentId(id);

        if (report != null) {
            return ResponseEntity.status(200).body(report);
        }
        return ResponseEntity.status(404).body(null);
    }

    // Return all medical reports of a specific patient
    @GetMapping("/patient/{id}/appointment/medical_report")
    public ResponseEntity<List<MedicalReportDTO>> getAllMedicalReportOfPatient(@PathVariable Long id) {
        List<MedicalReportDTO> reports= medicalReportService.getReportsByPatientId(id);
        if (reports != null) {
            return ResponseEntity.status(200).body(reports);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    // Save a new medical report
    @PostMapping("/medical_report")
    public ResponseEntity<MedicalReportDTO> saveMedicalReport(@RequestBody MedicalReportDTO report) {

        MedicalReportDTO medicalReport = medicalReportService.createReport(report);

        if (medicalReport != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(medicalReport);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
}
