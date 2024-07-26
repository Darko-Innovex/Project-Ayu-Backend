package lk.darkoinnovex.Ayu.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.darkoinnovex.Ayu.dto.LabReportDTO;
import lk.darkoinnovex.Ayu.service.LabReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
public class LabReportController {

    @Autowired
    private LabReportService labReportService;

    @Autowired
    private ObjectMapper objectMapper;
    
    // Return all lab reports of a specific patient
    @GetMapping("/patient/{id}/lab_reports") 
    public ResponseEntity<List<LabReportDTO>> getAllLabReportsOfPatient(
            @RequestParam("page") Integer page,
            @RequestParam("count") Integer count,
            @PathVariable Long id) {

        List<LabReportDTO> dtos = labReportService.getReportsByPatientId(id, page, count);

        if (dtos != null) {
            return  ResponseEntity.status(HttpStatus.OK).body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Find lab report by id
    @GetMapping("/lab_reports/{id}") 
    public ResponseEntity<?> getLabReportById(@PathVariable Long id) {

        byte[] pdfBytes = labReportService.getLabReportFileById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "report.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        if (pdfBytes != null) {
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Save lab report
    @PostMapping("/lab_report")
    public ResponseEntity<LabReportDTO> createLabReport(@RequestBody LabReportDTO labReportDTO) {

        labReportDTO = labReportService.createLabReport(labReportDTO);

        if (labReportDTO != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(labReportDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @PutMapping("/lab_report/{id}")
    public ResponseEntity<String> updateLabReport(@RequestBody MultipartFile file, @PathVariable Long id) {

        LabReportDTO labReportDTO = new LabReportDTO();
        labReportDTO.setFile(file);
        labReportDTO.setId(id);

        labReportDTO = labReportService.updateLabReport(labReportDTO);

        if (labReportDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Lab report updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Return all lab reports of a specific patient
    @GetMapping("/hospital/{id}/lab_reports")
    public ResponseEntity<List<LabReportDTO>> getAllLabReportsOfHospital(
            @RequestParam("page") Integer page,
            @RequestParam("count") Integer count,
            @PathVariable Long id) {

        List<LabReportDTO> dtos = labReportService.getReportsByHospitalId(id, page, count);

        if (dtos != null) {
            return  ResponseEntity.status(HttpStatus.OK).body(dtos);
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
