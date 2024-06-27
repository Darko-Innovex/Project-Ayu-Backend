package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.AllergyDTO;
import lk.darkoinnovex.Ayu.entity.Allergy;
import lk.darkoinnovex.Ayu.entity.Patient;
import lk.darkoinnovex.Ayu.repository.AllergyRepository;
import lk.darkoinnovex.Ayu.repository.PatientRepository;
import lk.darkoinnovex.Ayu.service.AllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllergyServiceImpl implements AllergyService {

    @Autowired
    private AllergyRepository allergyRepository;

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public AllergyDTO saveAllergy(AllergyDTO allergyDTO) {

        Patient patient = patientRepository.findById(allergyDTO.getPatientId()).orElse(null);

        if (patient != null) {
            Allergy allergy = new Allergy();

            allergy.setAllergy(allergyDTO.getAllergy());
            allergy.setPatient(patient);

            allergyRepository.save(allergy);

            allergyDTO.setId(allergy.getId());
            return allergyDTO;
        }

        return null;
    }

    @Override
    public AllergyDTO updateAllergy(AllergyDTO allergyDTO) {

        Allergy allergy = allergyRepository.findById(allergyDTO.getId()).orElse(null);
        Patient patient = patientRepository.findById(allergyDTO.getPatientId()).orElse(null);

        if (allergy != null) {
            allergy.setAllergy(allergyDTO.getAllergy());
            allergy.setPatient(patient);

            Allergy save = allergyRepository.save(allergy);

            if (save != null) {
                return allergyDTO;
            }
        }
        return null;
    }

    @Override
    public AllergyDTO findById(Long id) {

        Allergy allergy = allergyRepository.findById(id).orElse(null);

        if (allergy != null) {
            return new AllergyDTO(allergy.getId(), allergy.getAllergy(), allergy.getPatient().getId());
        }

        return null;
    }

    @Override
    public List<AllergyDTO> getAllAllergy() {
        List<Allergy> all = allergyRepository.findAll();

        if (all != null) {
            return all.stream().map(allergy -> new AllergyDTO(allergy.getId(), allergy.getAllergy(), allergy.getPatient().getId())).toList();
        }

        return null;
    }

    @Override
    public List<AllergyDTO> getAllergyByPatientId(Long patientId) {

        Patient patient = patientRepository.findById(patientId).orElse(null);

        if (patient != null) {

            List<Allergy> allergies = allergyRepository.findByPatientId(patient).orElse(null);

            if (allergies != null) {

                return allergies.stream().map(allergy -> new AllergyDTO(allergy.getId(), allergy.getAllergy(), allergy.getPatient().getId())).toList();

            }
        }

        return null;
    }
}
