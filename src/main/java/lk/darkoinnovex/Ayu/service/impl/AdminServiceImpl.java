package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.AdminDTO;
import lk.darkoinnovex.Ayu.dto.SignInDTO;
import lk.darkoinnovex.Ayu.entity.Admin;
import lk.darkoinnovex.Ayu.repository.AdminRepository;
import lk.darkoinnovex.Ayu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public AdminDTO configAdminSignIn(SignInDTO signInDTO) {
        Admin admin = adminRepository.findAdminBySignInInfo(signInDTO.getUsername(), signInDTO.getPassword()).orElse(null);

        if (admin != null) {

            AdminDTO adminDTO = new AdminDTO();
            adminDTO.setId(admin.getId());
            adminDTO.setName(admin.getName());
            adminDTO.setNic(admin.getNic());
            adminDTO.setEmail(admin.getEmail());

            return adminDTO;
        }
        return null;

    }
}
