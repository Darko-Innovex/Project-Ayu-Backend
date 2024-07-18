package lk.darkoinnovex.Ayu.service;

import lk.darkoinnovex.Ayu.dto.HealthCardDTO;
import lk.darkoinnovex.Ayu.dto.PatientDTO;
import org.springframework.stereotype.Service;

@Service
public interface HealthCardService {

    HealthCardDTO saveHealthCard(HealthCardDTO healthCardDTO);
    PatientDTO findByPinAndPassword(Long pin, short password);
}
