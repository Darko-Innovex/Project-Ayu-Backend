package lk.darkoinnovex.Ayu.controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.darkoinnovex.Ayu.dto.LabReportDTO;
import lk.darkoinnovex.Ayu.service.LabReportService;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Save lab report
    @PostMapping("/lab_report")
    public ResponseEntity<LabReportDTO> createLabReport(@RequestPart("doctor") String labReport,
                                                        @RequestPart("photo") MultipartFile reportFile) {

        try {

            LabReportDTO labReportDTO = objectMapper.readValue(labReport, LabReportDTO.class);
            labReportDTO.setFile(reportFile.getBytes());

            labReportDTO = labReportService.createLabReport(labReportDTO);

            if (labReportDTO != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(labReportDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
