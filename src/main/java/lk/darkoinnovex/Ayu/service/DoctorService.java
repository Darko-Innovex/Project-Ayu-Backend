package lk.darkoinnovex.Ayu.service;

import lk.darkoinnovex.Ayu.dto.DoctorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {

    List<DoctorDTO> getAllDoctors();

    DoctorDTO createDoctor(DoctorDTO dto);

    DoctorDTO updateDoctor(DoctorDTO dto);

    DoctorDTO getDoctorById(Long id);
}
