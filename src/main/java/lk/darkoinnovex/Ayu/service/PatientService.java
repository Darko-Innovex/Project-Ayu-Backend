package lk.darkoinnovex.Ayu.service;

import lk.darkoinnovex.Ayu.dto.OldPatientDTO;
import lk.darkoinnovex.Ayu.dto.PatientDTO;
import lk.darkoinnovex.Ayu.dto.SignInDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {

    List<PatientDTO> getAllPatients();

    PatientDTO createPatient(PatientDTO PatientDTO);

    PatientDTO updatePatient(PatientDTO PatientDTO);

    PatientDTO getPatientById(Long id);

    List<OldPatientDTO> getAllPatientByDoctorID(Long id);

    PatientDTO getPatientByHealthCard(Long pin);

    PatientDTO configPatientSignIn(SignInDTO dto);

    List<PatientDTO> getPatientSavedByHospital(Long id, Integer page, Integer count);
}
