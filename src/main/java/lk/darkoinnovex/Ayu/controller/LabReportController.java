package lk.darkoinnovex.Ayu.controller;

import java.util.List;

import lk.darkoinnovex.Ayu.dto.LabReportDTO;
import lk.darkoinnovex.Ayu.service.LabReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LabReportController {

    @Autowired
    private LabReportService labReportService;
    
    // Return all lab reports of a specific patient
    @GetMapping("/patient/{id}/lab_reports") 
    public ResponseEntity<List<LabReportDTO>> getAllLabReportsOfPatient(@PathVariable Long id) {

        List<LabReportDTO> dtos = labReportService.getReportsByPatientId(id);

        if (dtos != null) {
            return  ResponseEntity.status(HttpStatus.OK).body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Find lab report by id
    @GetMapping("/lab_reports/{id}") 
    public ResponseEntity<LabReportDTO> getLabReportById(@PathVariable Long id) {

        LabReportDTO dto = labReportService.getLabReportById(id);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /*
    // Return all lab reports of a specific patient on a specific date
    @GetMapping("/patient/{id}/lab_reports/date") 
    public ResponseEntity<List<LabReport>> getAllLabReportsOfPatientOnDate(@PathVariable Long id, @RequestParam LocalDate date) {
        return ResponseEntity.status(200).body(new ArrayList<LabReport>());
    }

    // Return all lab reports of a specific patient on a specific time period
    @GetMapping("/patient/{id}/lab_reports/time_period") 
    public ResponseEntity<List<LabReport>> getAllLabReportsOfPatientOnTimePeriod(@PathVariable Long id, @RequestParam LocalDate from, @RequestParam LocalDate to) {
        return ResponseEntity.status(200).body(new ArrayList<LabReport>());
    }*/
}
