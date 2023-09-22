package com.enviro.envirobank.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountTypeDto {
    private long id;
    private String accountTypeName;
    private BigDecimal minimumAmountRequired;
    private BigDecimal maximumOverdraftLimit;



}
