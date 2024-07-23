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
    List<DoctorDTO> findDoctorsByHospitalAndSpeciality(Long hospitalId, String speciality);
    List<DoctorDTO> getDoctorListOfHospital(Long hospitalId, Integer page, Integer count);
    DoctorDTO removeDoctorFromHospital(Long dId, Long hId);
    DoctorDTO addDoctorToHospital(Long dId, Long hId);
    Integer getDoctorCountOfHospital(Long id);
}
