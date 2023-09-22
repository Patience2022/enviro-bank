package com.enviro.envirobank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalResponse {
    private BigDecimal amountWithdrawn;
    private BigDecimal accountBalance;
    private BigDecimal amountAvailableForWithdrawal;
}
