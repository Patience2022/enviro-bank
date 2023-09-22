package com.enviro.envirobank.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AccountDto {
    private long accountId;
    private String accountNumber;
    private String customerFirstName;
    private String customerLastName;
    private BigDecimal balance;
    private Timestamp creationDate;
    private Timestamp closureDate;
    private BigDecimal overdraft;
    private BigDecimal amountAvailable;
    private boolean active;
    private String accountType;
}
