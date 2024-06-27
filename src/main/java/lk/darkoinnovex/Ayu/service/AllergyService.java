package lk.darkoinnovex.Ayu.service;

import lk.darkoinnovex.Ayu.dto.AllergyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AllergyService {

    AllergyDTO saveAllergy(AllergyDTO allergyDTO);

    AllergyDTO updateAllergy(AllergyDTO allergyDTO);

    AllergyDTO findById(Long id);

    List<AllergyDTO> getAllAllergy();

    List<AllergyDTO> getAllergyByPatientId(Long patientId);
}
