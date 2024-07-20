package lk.darkoinnovex.Ayu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lk.darkoinnovex.Ayu.dto.OldPatientDTO;
import lk.darkoinnovex.Ayu.service.AppointmentService;
import lk.darkoinnovex.Ayu.service.LabReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lk.darkoinnovex.Ayu.dto.PatientDTO;
import lk.darkoinnovex.Ayu.service.PatientService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private LabReportService labReportService;

    @Autowired
    private ObjectMapper objectMapper;

    // Find patient by id
    @GetMapping("/patient/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patient = patientService.getPatientById(id);

        if (patient != null) {
            return ResponseEntity.status(HttpStatus.OK).body(patient);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    // Update patient
    @PutMapping("/patient")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO dto = patientService.updatePatient(patientDTO);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Get all patients
    @GetMapping("/patient")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> dtos = patientService.getAllPatients();

        if (dtos != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //getPatientByHealthCardID
    @GetMapping("/card/{pin}/patient")
    public ResponseEntity<Object> getPatientByHealthCardID(@PathVariable Long pin) {

        PatientDTO patientDTO = patientService.getPatientByHealthCard(pin);

        if (patientDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(patientDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Find all patients of a doctor
    @GetMapping("/doctor/{id}/patient")
    public ResponseEntity<List<OldPatientDTO>> getAllPatientByDoctorID(@PathVariable Long id) {

        List<OldPatientDTO> patients = patientService.getAllPatientByDoctorID(id);

        if (patients != null) {
            return ResponseEntity.status(HttpStatus.OK).body(patients);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/patient/dashboard_data/{id}")
    public ResponseEntity<ObjectNode> getPatientDashboardData(@PathVariable Long id) {

        Integer completedAppointment = appointmentService.getCompletedAppointmentCountOfPatient(id);
        Integer pendingAppointment = appointmentService.getPendingAppointmentCountOfPatient(id);
        Integer labReportsCount = labReportService.getLabReportsCountOfPatient(id);

        ObjectNode json = objectMapper.createObjectNode();

        json.put("completedAppointment", completedAppointment);
        json.put("pendingAppointment", pendingAppointment);
        json.put("labReportsCount", labReportsCount);

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @GetMapping("/hospital/{id}/patient")
    public ResponseEntity<List<PatientDTO>> getPatientSavedByHospital(
            @PathVariable Long id, @RequestParam("page") Integer page, @RequestParam("count") Integer count) {

        List<PatientDTO> dtos = patientService.getPatientSavedByHospital(id, page, count);

        if (dtos != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
