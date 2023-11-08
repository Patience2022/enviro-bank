package com.enviro.envirobank.dto;

import com.enviro.envirobank.model.UserRole;
import com.enviro.envirobank.utils.ValidateId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class BankUserDto
{
    private long id;
    @NotBlank(message = "Please enter your name")
    private String firstName;
    @NotBlank(message = "Please enter your last name")
    private String lastName;
    @NotBlank(message = "Please enter your identity number")
//    @ValidateId(message = "Please enter a valid SA id number with 13 digits")
    private String identityNumber;
    @NotBlank(message = "Phone number can not be empty")
    @Pattern(regexp = "^0[0-9]{9}$", message="Please enter a valid phone number")
    private String phoneNumber;
    @NotBlank
    @Email
    private String email;
    private String userRolesName;
    private String userName;
    private boolean locked =false;
    private boolean enabled = true;
}



