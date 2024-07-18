package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.HealthCardDTO;
import lk.darkoinnovex.Ayu.dto.PatientDTO;
import lk.darkoinnovex.Ayu.entity.HealthCard;
import lk.darkoinnovex.Ayu.entity.Patient;
import lk.darkoinnovex.Ayu.repository.HealthCardRepository;
import lk.darkoinnovex.Ayu.repository.PatientRepository;
import lk.darkoinnovex.Ayu.service.HealthCardService;
import lk.darkoinnovex.Ayu.util.PinGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthCardServiceImpl implements HealthCardService {

    @Autowired
    private HealthCardRepository healthCardRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public HealthCardDTO saveHealthCard(HealthCardDTO healthCardDTO) {
        HealthCard entity = healthCardDTO.toEntity();
        entity.setPassword(PinGenerator.generateRandomFourDigitNumber());

        HealthCard save = healthCardRepository.save(entity);

        if (save != null) {
            return save.toDto();
        }

        return null;
    }

    @Override
    public PatientDTO findByPinAndPassword(Long pin, short password) {
        HealthCard healthCard = healthCardRepository.findByPinAndPassword(pin, password).orElse(null);

        if (healthCard != null) {
            Patient patient = patientRepository.getPatientByHealthCard(healthCard).orElse(null);

            if (patient != null) {
                return patient.toDto();
            }
        }

        return null;
    }
}
