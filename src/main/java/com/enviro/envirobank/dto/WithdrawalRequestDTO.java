package com.enviro.envirobank.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalRequestDTO {

    @NotBlank(message = "Please enter your account number")
    private String accountNumber;
    @Min(value = 50, message = "Minimum value to be entered is R 50")
    private BigDecimal amountToWithdraw;
}
