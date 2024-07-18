package lk.darkoinnovex.Ayu.service;

import lk.darkoinnovex.Ayu.dto.HospitalDTO;
import lk.darkoinnovex.Ayu.dto.SignInDTO;

import java.util.List;

public interface HospitalService {

    HospitalDTO getHospitalById(Long id);

    HospitalDTO configHospitalSignIn(SignInDTO signInDTO);

    List<String> getAllHospitalsLocations();

    List<HospitalDTO> getAllHospitalByLocation(String location);

    List<String> getAllSpecialityByHospital(Long hospitalId);
}
