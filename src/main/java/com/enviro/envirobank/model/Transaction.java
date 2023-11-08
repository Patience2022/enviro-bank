package com.enviro.envirobank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name="transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal amount;
    private Timestamp date;
    private String description;
    private String reference;
    private BigDecimal serviceFee;
    private String destination;
    @ManyToOne
    @JoinColumn(name = "type",referencedColumnName = "id")
    private TransactionType transactionType;
    @ManyToOne
    @JoinColumn(name = "account",referencedColumnName = "accountId")
    private Account account;
    private boolean pending =true;

}
