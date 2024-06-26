package lk.darkoinnovex.Ayu.controller;

import java.util.ArrayList;
import java.util.List;

import lk.darkoinnovex.Ayu.dto.AppointmentDTO;
import lk.darkoinnovex.Ayu.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lk.darkoinnovex.Ayu.entity.Appointment;

@RestController
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
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO) {

        AppointmentDTO dto = appointmentService.updateAppointment(appointmentDTO);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    public ResponseEntity<List<AppointmentDTO>> getAllAppointmentsOfPatient(@PathVariable Long id) {

        List<AppointmentDTO> dtos = appointmentService.getAppointmentsByPatientId(id);

        if (dtos != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /*
    // Return all appointment of a specific patient on a specific date
    @GetMapping("/patient/{id}/appointment/date")
    public ResponseEntity<List<Appointment>> getAllAppointmentOfPatientOnDate(@PathVariable Long id, @RequestParam LocalDate date) {
        return ResponseEntity.status(200).body(new ArrayList<Appointment>());
    }

    // Return all appointment of a specific patient on a specific time period
    @GetMapping("/patient/{id}/appointment/time_period")
    public ResponseEntity<List<Appointment>> getAllAppointmentOfPatientOnTimePeriod(@PathVariable Long id, @RequestParam LocalDate from, @RequestParam LocalDate to) {
        return ResponseEntity.status(200).body(new ArrayList<Appointment>());
    }*/
}
