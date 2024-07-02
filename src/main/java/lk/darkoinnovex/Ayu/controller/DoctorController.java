package lk.darkoinnovex.Ayu.controller;

import lk.darkoinnovex.Ayu.dto.DoctorDTO;
import lk.darkoinnovex.Ayu.dto.OldPatientDTO;
import lk.darkoinnovex.Ayu.dto.SignInDTO;
import lk.darkoinnovex.Ayu.service.DoctorService;
import lk.darkoinnovex.Ayu.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    // Return all doctors that patient placed appointments
    @GetMapping("/patient/{id}/doctor")
    public ResponseEntity<List<DoctorDTO>> getAllDoctorsOfPatient(@PathVariable Long id) {

        List<DoctorDTO> doctorsOfPatient = doctorService.getDoctorsOfPatient(id);

        if (doctorsOfPatient != null) {
            return ResponseEntity.status(HttpStatus.OK).body(doctorsOfPatient);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Find all patients of a doctor
    public ResponseEntity<List<OldPatientDTO>> getAllPatientByDoctorID(@PathVariable Long id) {

        List<OldPatientDTO> patients = patientService.getAllPatientByDoctorID(id);

        if (patients != null) {
            return ResponseEntity.status(HttpStatus.OK).body(patients);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //getDoctorById
    @GetMapping("/doctor/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {

        DoctorDTO doctor = doctorService.getDoctorById(id);

        if (doctor != null) {
            return ResponseEntity.status(HttpStatus.OK).body(doctor);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //updateDoctor
    @PutMapping("/doctor")
    public ResponseEntity<DoctorDTO> updateDoctor(DoctorDTO doctorDTO) {

        DoctorDTO dto = doctorService.updateDoctor(doctorDTO);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Confirm username and password of a doctor
    @GetMapping("/doctor/sign-in")
    public ResponseEntity<DoctorDTO> doctorSignInConfig(@RequestParam String username, @RequestParam String password) {
        DoctorDTO doctorDTO = doctorService.configDoctorSignIn(new SignInDTO(username, password));

        if (doctorDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(doctorDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //getReviewsByDoctor
    @GetMapping("/doctor/{id}/reviews")
    public ResponseEntity<Object> getReviewsByDoctor(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
