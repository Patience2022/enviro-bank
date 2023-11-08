package com.enviro.envirobank.service;

import com.enviro.envirobank.dto.ChangePasswordRequest;
import com.enviro.envirobank.model.BankUser;

public interface BankUserService {
 String id(String username);
  BankUser findByUsernameOremail(String username);
}
