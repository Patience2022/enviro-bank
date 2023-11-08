package com.enviro.envirobank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ListCustomerDto {

    private String firstName;
    private String lastName;
    private String email;
    private int numberOfAccounts;
}
