package com.enviro.envirobank.service.impl;

import com.enviro.envirobank.model.UserRole;
import com.enviro.envirobank.repository.UserRolesRepository;
import com.enviro.envirobank.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRolesServiceImpl implements UserRoleService {

    private final UserRolesRepository userRolesRepository;

    @Override
    public UserRole getUserRole(String roleName) {
        return userRolesRepository.findByName(roleName).orElseThrow();
    }
}
