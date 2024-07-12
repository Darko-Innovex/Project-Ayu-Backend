package lk.darkoinnovex.Ayu.controller;

import lk.darkoinnovex.Ayu.dto.DoctorDTO;
import lk.darkoinnovex.Ayu.dto.PatientDTO;
import lk.darkoinnovex.Ayu.dto.SignInDTO;
import lk.darkoinnovex.Ayu.service.DoctorService;
import lk.darkoinnovex.Ayu.service.PatientService;
import lk.darkoinnovex.Ayu.util.BaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class RegistrationController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    // Confirm username and password
    @GetMapping("/sign-in")
    public ResponseEntity<? extends BaseDTO> SignInConfig(@RequestBody SignInDTO dto) {
        PatientDTO patientDTO = patientService.configPatientSignIn(dto);

        if (patientDTO != null) {

            return ResponseEntity.status(HttpStatus.OK).body(patientDTO);

        } else {
            DoctorDTO doctorDTO = doctorService.configDoctorSignIn(dto);

            if (doctorDTO != null) {

                return ResponseEntity.status(HttpStatus.OK).body(doctorDTO);

            } else {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
    }
}
