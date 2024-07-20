package lk.darkoinnovex.Ayu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.darkoinnovex.Ayu.dto.DoctorDTO;
import lk.darkoinnovex.Ayu.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ObjectMapper objectMapper;

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

    //getDoctorById
    @GetMapping("/doctor/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {

        DoctorDTO doctor = doctorService.getDoctorById(id);

        if (doctor != null) {
            System.out.println(doctor);
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

    @PostMapping("/doctor/{id}")
    public ResponseEntity<DoctorDTO> saveDoctor(@RequestPart("doctor") String doctor,
                                                @RequestPart("photo") MultipartFile photoFile, @PathVariable Long id) {

        try {
            DoctorDTO dto = objectMapper.readValue(doctor, DoctorDTO.class);
            dto.setPhoto(photoFile.getBytes());
            System.out.println(dto);
            dto = doctorService.createDoctor(dto,id);

            if (dto != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(dto);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //getReviewsByDoctor
    @GetMapping("/doctor/{id}/reviews")
    public ResponseEntity<Object> getReviewsByDoctor(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Get all doctor by speciality and hospital
    @GetMapping("/hospital/{id}/speciality/{speciality}/doctor")
    public ResponseEntity<List<DoctorDTO>> getDoctorBySpecialityAndHospital(
            @PathVariable("speciality") String speciality,
            @PathVariable("id") Long hospital) {

        List<DoctorDTO> doctors = doctorService.findDoctorsByHospitalAndSpeciality(hospital, speciality);

        if (doctors != null) {
            return ResponseEntity.status(HttpStatus.OK).body(doctors);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Get doctor list of a specific hospital
    @GetMapping("/hospital/{id}/doctor")
    public ResponseEntity<List<DoctorDTO>> getDoctorListOfHospital(
            @PathVariable Long id, @RequestParam("page") Integer page, @RequestParam("count") Integer count) {

        List<DoctorDTO> list = doctorService.getDoctorListOfHospital(id, page, count);

        if (list != null) {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
