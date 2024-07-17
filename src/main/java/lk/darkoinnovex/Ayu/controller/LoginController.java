package lk.darkoinnovex.Ayu.controller;

import lk.darkoinnovex.Ayu.dto.AdminDTO;
import lk.darkoinnovex.Ayu.dto.DoctorDTO;
import lk.darkoinnovex.Ayu.dto.HospitalDTO;
import lk.darkoinnovex.Ayu.dto.PatientDTO;
import lk.darkoinnovex.Ayu.dto.SignInDTO;
import lk.darkoinnovex.Ayu.service.AdminService;
import lk.darkoinnovex.Ayu.service.DoctorService;
import lk.darkoinnovex.Ayu.service.HospitalService;
import lk.darkoinnovex.Ayu.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/auth/signIn")
    public ResponseEntity<SignInDTO> SignInConfig(@RequestBody SignInDTO dto) {

        PatientDTO patientDTO = patientService.configPatientSignIn(dto);
        DoctorDTO doctorDTO = doctorService.configDoctorSignIn(dto);
        HospitalDTO hospitalDTO = hospitalService.configHospitalSignIn(dto);
        AdminDTO adminDTO = adminService.configAdminSignIn(dto);

        System.out.println(patientDTO);
        System.out.println(doctorDTO);
        System.out.println(hospitalDTO);
        System.out.println(adminDTO);

        if(patientDTO != null && doctorDTO != null) {
            dto.setType("patient/doctor");
            dto.setPatientId(patientDTO.getId());
            dto.setDoctorId(doctorDTO.getId());
            return ResponseEntity.ok(dto);
        }else if(patientDTO != null) {
            dto.setType("patient");
            dto.setPatientId(patientDTO.getId());
            return ResponseEntity.ok(dto);
        }else if(doctorDTO != null) {
            dto.setType("doctor");
            dto.setDoctorId(doctorDTO.getId());
            return ResponseEntity.ok(dto);
        } else if (hospitalDTO != null){
            dto.setHospitalId(hospitalDTO.getId());
            dto.setType("hospital");
            return ResponseEntity.ok(dto);
        } else if (adminDTO != null) {
            dto.setAdminId(adminDTO.getId());
            dto.setType("admin");
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
