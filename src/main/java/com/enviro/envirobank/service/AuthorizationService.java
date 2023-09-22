package com.enviro.envirobank.service;

import com.enviro.envirobank.dto.LoginRequest;

public interface AuthorizationService {

    String login(LoginRequest request);

}
