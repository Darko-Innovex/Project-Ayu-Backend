package lk.darkoinnovex.Ayu.security;

import lk.darkoinnovex.Ayu.entity.Patient;
import lk.darkoinnovex.Ayu.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PatientRepository patientRepository;
    {
        System.out.println("UserDetailServiceImpl");
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        //TODO: get patient by email or nic
        Patient patient = patientRepository.getPatientByNic(username).orElse(null);

        if (patient == null) {
            throw new UsernameNotFoundException(username + " not found");
        } else {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(username)
                    .password(patient.getPassword())
                    .build();
        }
    }
}