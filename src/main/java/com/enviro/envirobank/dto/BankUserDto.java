package com.enviro.envirobank.dto;

import com.enviro.envirobank.model.UserRole;
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
    @Pattern(regexp = "^[0-9]{13}$",message = "Please enter a valid identity number with 13 digits")
    private String identityNumber;
    @NotBlank(message = "Phone number can not be empty")
    @Pattern(message = "Please enter a valid phone number without country code",regexp = "^0[0-9]{9}$")
    private String phoneNumber;
    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "Please choose a username")
    private String userRoles;
    private String userName;
    private boolean locked = true;
    private boolean enabled =false;
}



