package com.enviro.envirobank.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    @NotEmpty(message = "Please enter the account number to transfer from")
    private String fromAccountNumber;
    @NotEmpty(message = "Please enter the account number to transfer to")
    private String toAccountNumber;
    @NotNull(message = "Amount cannot be empty")
    private BigDecimal transferAmount;
    private String reference;
}
