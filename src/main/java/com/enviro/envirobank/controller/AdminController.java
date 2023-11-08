package com.enviro.envirobank.controller;

import com.enviro.envirobank.dto.AdminDto;
import com.enviro.envirobank.model.Admin;
import com.enviro.envirobank.model.UserRole;
import com.enviro.envirobank.service.AdminService;
import com.enviro.envirobank.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admins")
public class AdminController {
    private final ModelMapper modelMapper;
    private final AdminService adminService;
    private final UserRoleService roleService;


    @PostMapping("create-admin")
    public AdminDto createAdmin(@RequestBody AdminDto adminDto){
        Admin admin = modelMapper.map(adminDto, Admin.class);
        UserRole userRole = roleService.getUserRole(adminDto.getUserRolesName());
        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);
        admin.setUserRoles(roles);
        return modelMapper.map(adminService.createAdmin(admin), AdminDto.class);
    }

    @GetMapping("{id}")
    public Admin getAdminById(@PathVariable Long id){
        return adminService.getAdminbyId(id);
    }

}
