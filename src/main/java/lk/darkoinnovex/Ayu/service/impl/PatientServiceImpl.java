package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.PatientDTO;
import lk.darkoinnovex.Ayu.entity.HealthCard;
import lk.darkoinnovex.Ayu.entity.Hospital;
import lk.darkoinnovex.Ayu.entity.Patient;
import lk.darkoinnovex.Ayu.repository.HealthCardRepository;
import lk.darkoinnovex.Ayu.repository.HospitalRepository;
import lk.darkoinnovex.Ayu.repository.PatientRepository;
import lk.darkoinnovex.Ayu.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private HealthCardRepository healthCardRepository;

    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientDTO> patientDTOS = patients.stream().map(patient -> new PatientDTO(patient.getId(), patient.getName(), patient.getDob(), patient.getNic(), patient.getMobile(), patient.getEmail(), patient.getBloodType(), patient.getPassword(), patient.getHealthCard().getPinNo(), patient.getHospital().getId())).toList();
        return patientDTOS;
    }

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        HealthCard healthCard = healthCardRepository.findByPin(patientDTO.getHealthCardPin()).orElse(null);
        Hospital hospital = hospitalRepository.findById(patientDTO.getHospitalId()).orElse(null);

        if (healthCard != null || hospital != null) {

            Patient patient = new Patient();
            patient.setBloodType(patientDTO.getBloodType());
            patient.setDob(patientDTO.getDob());
            patient.setEmail(patientDTO.getEmail());
            patient.setMobile(patientDTO.getMobile());
            patient.setName(patientDTO.getName());
            patient.setNic(patientDTO.getNic());
            patient.setPassword(patientDTO.getPassword());
            patient.setHealthCard(healthCard);
            patient.setHospital(hospital);

            Patient savedPatient = patientRepository.save(patient);
            return new PatientDTO(savedPatient.getId(), savedPatient.getBloodType(), savedPatient.getDob(), savedPatient.getEmail(), savedPatient.getMobile(), savedPatient.getName(), savedPatient.getNic(), savedPatient.getPassword(), savedPatient.getHealthCard().getPinNo(), savedPatient.getHospital().getId());
        }
        return null;
    }

    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO) {

        Patient patient = patientRepository.findById(patientDTO.getId()).orElse(null);

        if (patient != null) {
            HealthCard healthCard = healthCardRepository.findByPin(patientDTO.getHealthCardPin()).orElse(null);
            Hospital hospital = hospitalRepository.findById(patientDTO.getHospitalId()).orElse(null);

            if (healthCard != null || hospital != null) {
                patient.setBloodType(patientDTO.getBloodType());
                patient.setDob(patientDTO.getDob());
                patient.setEmail(patientDTO.getEmail());
                patient.setMobile(patientDTO.getMobile());
                patient.setName(patientDTO.getName());
                patient.setNic(patientDTO.getNic());
                patient.setPassword(patientDTO.getPassword());
                patient.setHealthCard(healthCard);
                patient.setHospital(hospital);

                Patient updatedPatient = patientRepository.save(patient);
                return new PatientDTO(updatedPatient.getId(), updatedPatient.getBloodType(), updatedPatient.getDob(), updatedPatient.getEmail(), updatedPatient.getMobile(), updatedPatient.getName(), updatedPatient.getNic(), updatedPatient.getPassword(), updatedPatient.getHealthCard().getPinNo(), updatedPatient.getHospital().getId());
            }
        }

        return null;
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient != null) {
            return new PatientDTO(patient.getId(), patient.getBloodType(), patient.getDob(), patient.getEmail(), patient.getMobile(), patient.getName(),  patient.getNic(),   patient.getPassword(), patient.getHealthCard().getPinNo(), patient.getHospital().getId());
        }
        return null;
    }
}
