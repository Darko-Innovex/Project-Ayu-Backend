package lk.darkoinnovex.Ayu.service;

import lk.darkoinnovex.Ayu.dto.AdminDTO;
import lk.darkoinnovex.Ayu.dto.SignInDTO;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    AdminDTO configAdminSignIn(SignInDTO signInDTO);
}
