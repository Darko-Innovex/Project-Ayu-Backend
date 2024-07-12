package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.HospitalDTO;
import lk.darkoinnovex.Ayu.dto.SignInDTO;
import lk.darkoinnovex.Ayu.entity.Hospital;
import lk.darkoinnovex.Ayu.repository.HospitalRepository;
import lk.darkoinnovex.Ayu.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public HospitalDTO configHospitalSignIn(SignInDTO signInDTO) {
        Hospital hospital = hospitalRepository.findHospitalBySignInInfo(signInDTO.getUsername(), signInDTO.getPassword()).orElse(null);

        if(hospital != null){
            HospitalDTO hospitalDTO = new HospitalDTO();
            hospitalDTO.setId(hospital.getId());
            hospitalDTO.setName(hospital.getName());
            hospitalDTO.setLocation(hospital.getLocation());
            hospitalDTO.setEmail(hospital.getEmail());
            hospitalDTO.setMobile(hospital.getMobile());
            hospitalDTO.setPassword(hospital.getPassword());
            hospitalDTO.setStatus(hospital.getStatus());
            return hospitalDTO;
        }
        return null;
    }
}
