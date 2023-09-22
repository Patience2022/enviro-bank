package com.enviro.envirobank.service.impl;

import com.enviro.envirobank.model.Admin;
import com.enviro.envirobank.repository.AdminRepository;
import com.enviro.envirobank.service.AdminService;
import com.enviro.envirobank.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Admin createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode("admin"));

        Admin savedAdmin = adminRepository.save(admin);
        return savedAdmin;
    }

    @Override
    public Admin getAdminbyId(long id) {
        return adminRepository.findById(id).orElseThrow();
    }
}
