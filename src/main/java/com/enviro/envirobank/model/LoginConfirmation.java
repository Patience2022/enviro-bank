package com.enviro.envirobank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="login_tokens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class LoginConfirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDate createdAt;
    private LocalDate expireAt;
    private LocalDate confirmedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "bank_user")
    private BankUser bankUser;

}
