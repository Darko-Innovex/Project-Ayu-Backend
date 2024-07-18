package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.HospitalDTO;
import lk.darkoinnovex.Ayu.dto.SignInDTO;
import lk.darkoinnovex.Ayu.entity.Hospital;
import lk.darkoinnovex.Ayu.repository.HospitalRepository;
import lk.darkoinnovex.Ayu.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public HospitalDTO getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id).orElse(null);

        if (hospital != null) {
            HospitalDTO hospitalDTO = new HospitalDTO();

            hospitalDTO.setId(hospital.getId());
            hospitalDTO.setName(hospital.getName());
            hospitalDTO.setLocation(hospital.getLocation());
            hospitalDTO.setEmail(hospital.getEmail());
            hospitalDTO.setMobile(hospital.getMobile());
            hospitalDTO.setStatus(hospital.getStatus());

            return hospitalDTO;
        }

        return null;
    }

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
            hospitalDTO.setStatus(hospital.getStatus());

            return hospitalDTO;
        }
        return null;
    }

    @Override
    public List<String> getAllHospitalsLocations() {
        List<String> locations = hospitalRepository.findAllHospitalsLocations().orElse(null);

        if (locations != null) {
            return locations;
        }

        return null;
    }

    @Override
    public List<HospitalDTO> getAllHospitalByLocation(String location) {
        List<Hospital> hospitals = hospitalRepository.findAllHospitalByLocation(location).orElse(null);

        if (hospitals != null) {
            return hospitals.stream().map(hospital ->
                    new HospitalDTO(
                            hospital.getId(),
                            hospital.getName(),
                            hospital.getEmail(),
                            hospital.getMobile(),
                            hospital.getLocation(),
                            hospital.getStatus()
                    )
            ).toList();
        }

        return null;
    }

    @Override
    public List<String> getAllSpecialityByHospital(Long hospitalId) {
        List<String> speciality = hospitalRepository.findAllSpecialties(hospitalId).orElse(null);

        if (speciality != null) {
            return speciality;
        }

        return null;
    }
}
