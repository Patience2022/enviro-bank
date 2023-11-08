package com.enviro.envirobank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {

    private String newPassword;
    private String oldPassword;
    private String confirmationPassword;
}

