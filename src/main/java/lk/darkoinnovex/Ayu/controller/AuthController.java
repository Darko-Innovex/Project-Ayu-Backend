package lk.darkoinnovex.Ayu.controller;

import lk.darkoinnovex.Ayu.dto.*;
import lk.darkoinnovex.Ayu.security.jwt.JwtUtils;
import lk.darkoinnovex.Ayu.service.AdminService;
import lk.darkoinnovex.Ayu.service.DoctorService;
import lk.darkoinnovex.Ayu.service.HospitalService;
import lk.darkoinnovex.Ayu.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/auth/signIn")
    public ResponseEntity<SignInDTO> SignInConfig(@RequestBody SignInDTO dto) {

        PatientDTO patientDTO = patientService.configPatientSignIn(dto);
        DoctorDTO doctorDTO = doctorService.configDoctorSignIn(dto);
        HospitalDTO hospitalDTO = hospitalService.configHospitalSignIn(dto);
        AdminDTO adminDTO = adminService.configAdminSignIn(dto);

        if(patientDTO != null && doctorDTO != null) {

            System.out.println("Patient or Doctor Sign In Success");
            dto.setType("patient/doctor");
            dto.setPatientId(patientDTO.getId());
            dto.setDoctorId(doctorDTO.getId());
            setToken(dto);
            return ResponseEntity.ok(dto);

        }else if(patientDTO != null) {

            System.out.println("Patient Sign In Success");
            dto.setType("patient");
            dto.setPatientId(patientDTO.getId());
            setToken(dto);
            return ResponseEntity.ok(dto);

        }else if(doctorDTO != null) {

            System.out.println("Doctor Sign In Success");
            dto.setType("doctor");
            dto.setDoctorId(doctorDTO.getId());
            setToken(dto);
            return ResponseEntity.ok(dto);

        } else if (hospitalDTO != null){

            System.out.println("Hospital Sign In Success");
            dto.setHospitalId(hospitalDTO.getId());
            dto.setType("hospital");
            setToken(dto);
            return ResponseEntity.ok(dto);

        } else if (adminDTO != null) {

            System.out.println("Admin Sign In Success");
            dto.setAdminId(adminDTO.getId());
            dto.setType("admin");
            setToken(dto);
            return ResponseEntity.ok(dto);
        }

        System.out.println("Sign in failed");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Register new Patient

    @PostMapping("/auth/signUp")
    public ResponseEntity<String> SignUpConfig(@RequestBody PatientDTO dto) {

        dto.setDob(LocalDate.now());
        PatientDTO patient = patientService.createPatient(dto);

        if (patient != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Patient created");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient not created");
        }
    }

    private void setToken(SignInDTO dto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String token = jwtUtils.generateToken(authenticate);
        System.out.println(token);
        dto.setToken(token);
    }
}
