package lk.darkoinnovex.Ayu.controller;

import java.time.LocalDate;
import java.util.List;

import lk.darkoinnovex.Ayu.dto.AppointmentDTO;
import lk.darkoinnovex.Ayu.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    
    // Save appointment
    @PostMapping("/appointment")
    public ResponseEntity<AppointmentDTO> saveAppointment(@RequestBody AppointmentDTO appointmentDTO) {

        AppointmentDTO dto = appointmentService.createAppointment(appointmentDTO);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Update an appointment
    @PutMapping("/appointment/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable Long id) {

        AppointmentDTO dto = appointmentService.updateAppointment(id);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Appointment canceled");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Find appointment by id
    @GetMapping("/appointment/{id}") 
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id) {

        AppointmentDTO appointment = appointmentService.getAppointmentById(id);

        if (appointment != null) {
            return ResponseEntity.status(HttpStatus.OK).body(appointment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Return all appointments of a specific patient
    @GetMapping("/patient/{id}/appointment") 
    public ResponseEntity<List<AppointmentDTO>> getAllAppointmentsOfPatient(@RequestParam("page") Integer page,
            @RequestParam("count") Integer count, @PathVariable Long id) {

        List<AppointmentDTO> dtos = appointmentService.getAppointmentsByPatientId(id, page, count);

        if (dtos != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/appointment/count/{id}")
    public ResponseEntity<Integer> getAppointmentCount(@PathVariable Long id) {

        return ResponseEntity.ok().body(appointmentService.getAppointmentCountOfPatient(id));
    }

    // Return all appointment of a specific patient on a specific date
    @GetMapping("/patient/{id}/appointment/date")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointmentOfPatientOnDate(
            @PathVariable Long id,
            @RequestParam("date") LocalDate date,
            @RequestParam("page") Integer page,
            @RequestParam("count") Integer count) {

        List<AppointmentDTO> dtos = appointmentService.getAllAppointmentOfPatientOnDate(id, date, page, count);

        if (dtos != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Return all appointment of a specific patient on a specific time period
    @GetMapping("/patient/{id}/appointment/time_period")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointmentOfPatientOnTimePeriod(
            @PathVariable Long id,
            @RequestParam("from") LocalDate from,
            @RequestParam("to") LocalDate to,
            @RequestParam("page") Integer page,
            @RequestParam("count") Integer count) {

        List<AppointmentDTO> dtos = appointmentService.findAppointmentsByPatientIdAndDateRange(id, from, to, page, count);

        if (dtos != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/hospital/{id}/appointment")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentOfHospital(
            @PathVariable Long id, @RequestParam("page") Integer page, @RequestParam("count") Integer count) {

        List<AppointmentDTO> dtos = appointmentService.getAppointmentOfHospital(id, page, count);

        if (dtos != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
