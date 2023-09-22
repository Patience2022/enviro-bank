package com.enviro.envirobank.service;

import com.enviro.envirobank.model.Admin;

public interface AdminService {
    Admin createAdmin(Admin admin);
    Admin getAdminbyId(long id);
}
