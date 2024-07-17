package lk.darkoinnovex.Ayu.service;

import lk.darkoinnovex.Ayu.dto.HospitalDTO;
import lk.darkoinnovex.Ayu.dto.SignInDTO;

public interface HospitalService {

    HospitalDTO getHospitalById(Long id);

    HospitalDTO configHospitalSignIn(SignInDTO signInDTO);
}
