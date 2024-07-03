package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.HealthCardDTO;
import lk.darkoinnovex.Ayu.dto.OldPatientDTO;
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

import java.util.ArrayList;
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
        List<PatientDTO> patientDTOS = new ArrayList<>();

        patients.forEach(patient -> {
            patientDTOS.add(patient.toDto());
        });
        return patientDTOS;
    }

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {

        HealthCard healthCard = null;

        if (patientDTO.getHealthCardPin() == null) {
            List<HealthCard> healthCards = healthCardRepository.getNotReservedHealthCard().orElse(null);

            if (healthCards != null) {
                healthCard = healthCards.get(0);
            }

        } else {
            healthCard = healthCardRepository.findByPin(patientDTO.getHealthCardPin()).orElse(null);
        }

        Hospital hospital = null;

        if (patientDTO.getHospitalId() != null) {
            hospital = hospitalRepository.findById(patientDTO.getHospitalId()).orElse(null);
        }

        if (healthCard != null) {

            Patient patient = patientDTO.toEntity();
            patient.setHealthCard(healthCard);
            patient.setHospital(hospital);

            Patient savedPatient = patientRepository.save(patient);

            if (savedPatient != null) {

                healthCard.setStatus("Connected");

                HealthCard save = healthCardRepository.save(healthCard);

                return savedPatient.toDto();
            }
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
                patient.setName(patientDTO.getFirstName() + " " + patientDTO.getLastName());
                patient.setNic(patientDTO.getNic());
                patient.setPassword(patientDTO.getPassword());
                patient.setHealthCard(healthCard);
                patient.setHospital(hospital);

                Patient updatedPatient = patientRepository.save(patient);

                return updatedPatient.toDto();
            }
        }

        return null;
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient != null) {
            return patient.toDto();
        }
        return null;
    }

    @Override
    public List<OldPatientDTO> getAllPatientByDoctorID(Long id) {
        List<OldPatientDTO> dtos = patientRepository.getOldPatientsOfDoctor(id).orElse(null);

        if (dtos != null) {
            return dtos;
        }

        return null;
    }
}
