package lk.darkoinnovex.Ayu.service;

import lk.darkoinnovex.Ayu.dto.PatientDTO;
import lk.darkoinnovex.Ayu.entity.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {

    List<PatientDTO> getAllPatients();
    PatientDTO createPatient(PatientDTO PatientDTO);
    PatientDTO updatePatient(PatientDTO PatientDTO);
    PatientDTO getPatientById(Long id);

}
