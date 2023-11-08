package com.enviro.envirobank.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;


public interface TransactionDTO {
     long getId();
     BigDecimal getAmount();
     Timestamp getDate();
     String getDescription();
     String getReference();
     BigDecimal getServiceFee();
     String getDestination();
     String getTransactionType();
     String getAccountNumber();
     boolean isPending();
     String getAccountType();
}
