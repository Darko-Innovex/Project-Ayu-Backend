package lk.darkoinnovex.Ayu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lk.darkoinnovex.Ayu.dto.HospitalDTO;
import lk.darkoinnovex.Ayu.service.AppointmentService;
import lk.darkoinnovex.Ayu.service.HospitalService;
import lk.darkoinnovex.Ayu.service.LabReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    @Qualifier("appointmentServiceImpl")
    private AppointmentService appointmentService;

    @Autowired
    private ObjectMapper objectMapper;

    // Find hospital by id
    @GetMapping("/hospital/{id}")
    public ResponseEntity<HospitalDTO> getHospitalById(@PathVariable Long id) {
        System.out.println(id);
        HospitalDTO dto = hospitalService.getHospitalById(id);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Find all hospital locations
    @GetMapping("/hospital/location")
    public ResponseEntity<List<String>> getAllHospitalsLocation() {
        List<String> locations = hospitalService.getAllHospitalsLocations();

        if (locations != null) {
            return ResponseEntity.status(HttpStatus.OK).body(locations);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Find all hospitals in a location
    @GetMapping("/location/hospital")
    public ResponseEntity<List<HospitalDTO>> getAllHospitalByLocation(@RequestParam String location) {
        List<HospitalDTO> hospitals = hospitalService.getAllHospitalByLocation(location);

        if (hospitals != null) {
            return ResponseEntity.status(HttpStatus.OK).body(hospitals);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Get all specialities of doctors
    @GetMapping("/hospital/{id}/speciality")
    public ResponseEntity<List<String>> getSpecialitiesByHospital(@PathVariable Long id) {
        List<String> speciality = hospitalService.getAllSpecialityByHospital(id);

        if (speciality != null) {
            return ResponseEntity.status(HttpStatus.OK).body(speciality);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Get dashboard data of a patient
    @GetMapping("/hospital/dashboard_data/{id}")
    public ResponseEntity<ObjectNode> getPatientDashboardData(@PathVariable Long id) {
        Integer completedAppointment = appointmentService.getCompletedAppointmentCountOfHospital(id);
        Integer pendingAppointment = appointmentService.getPendingAppointmentCountOfHospital(id);
//        Integer labReportsCount = LabReportService.getLabReportsCountOfHospital(id);

        ObjectNode json = objectMapper.createObjectNode();
        json.put("completedAppointment", completedAppointment);
        json.put("pendingAppointment", pendingAppointment);

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }
}
