package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.OldPatientDTO;
import lk.darkoinnovex.Ayu.dto.PatientDTO;
import lk.darkoinnovex.Ayu.dto.SignInDTO;
import lk.darkoinnovex.Ayu.entity.HealthCard;
import lk.darkoinnovex.Ayu.entity.Hospital;
import lk.darkoinnovex.Ayu.entity.Patient;
import lk.darkoinnovex.Ayu.repository.HealthCardRepository;
import lk.darkoinnovex.Ayu.repository.HospitalRepository;
import lk.darkoinnovex.Ayu.repository.PatientRepository;
import lk.darkoinnovex.Ayu.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {

        HealthCard healthCard = null;
        Hospital hospital = null;

        if (patientDTO.getHospitalId() != null) {
            hospital = hospitalRepository.findById(patientDTO.getHospitalId()).orElse(null);
        }

        if (patientDTO.getHealthCardPin() != null) {
            HealthCard card = healthCardRepository.findByPin(patientDTO.getHealthCardPin()).orElse(null);

            if (card != null && card.getStatus().equals("Pending")) {
                healthCard = card;
            }
        }

        Patient patient = patientDTO.toEntity();
        patient.setHospital(hospital);
        patient.setHealthCard(healthCard);

        Patient savedPatient = patientRepository.save(patient);

        if (savedPatient != null) {

            healthCard.setStatus("Connected");

            HealthCard save = healthCardRepository.save(healthCard);

            if (save != null) {
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

    @Override
    public PatientDTO getPatientByHealthCard(Long pin, short password) {
        HealthCard healthCard = healthCardRepository.findByPinAndPassword(pin, password).orElse(null);

        if (healthCard != null) {
            Patient patient = patientRepository.getPatientByHealthCard(healthCard).orElse(null);

            if (patient != null) {
                return patient.toDto();
            }
        }

        return null;
    }

    @Override
    public PatientDTO configPatientSignIn(SignInDTO dto) {
        Patient patient = patientRepository.findPatientBySignInInfo(dto.getUsername(), dto.getPassword()).orElse(null);

        if (patient != null) {
            return patient.toDto();
        }

        return null;
    }

    @Override
    public List<PatientDTO> getPatientSavedByHospital(Long id, Integer page, Integer count) {
        Pageable pageable = PageRequest.of(page, count);

        Page<Patient> patients = patientRepository.getPatientSavedByHospital(id, pageable);

        if (!patients.isEmpty()) {
            List<PatientDTO> patientDTOS = new ArrayList<>();

            patients.forEach(patient -> {
                patientDTOS.add(patient.toDto());
            });

            return patientDTOS;
        }

        return null;
    }

    @Override
    public PatientDTO getPatientByNic(String nic) {
        Patient patient = patientRepository.getPatientByNic(nic).orElse(null);

        if (patient != null) {
            return patient.toDto();
        }

        return null;
    }

    @Transactional
    @Override
    public PatientDTO addHealthCardToPatient(Long pId, Long index) {

        Patient patient = patientRepository.findById(pId).orElse(null);

        if (patient != null && patient.getHealthCard() != null) {

            HealthCard healthCard = healthCardRepository.findByPin(index).orElse(null);

            if (healthCard != null && healthCard.getStatus().equals("Pending")) {
                patient.setHealthCard(healthCard);
                Patient save = patientRepository.save(patient);

                if (save != null) {
                    healthCard.setStatus("Connected");
                    healthCardRepository.save(healthCard);

                    return save.toDto();
                }
            }
        }

        return null;
    }
}
