package lk.darkoinnovex.Ayu.service;

import lk.darkoinnovex.Ayu.dto.DoctorDTO;
import lk.darkoinnovex.Ayu.dto.SignInDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {

    List<DoctorDTO> getAllDoctors();

    DoctorDTO createDoctor(DoctorDTO dto, Long id);

    DoctorDTO updateDoctor(DoctorDTO dto);

    DoctorDTO getDoctorById(Long id);

    List<DoctorDTO> getDoctorsOfPatient(Long id);

    DoctorDTO configDoctorSignIn(SignInDTO dto);
}
