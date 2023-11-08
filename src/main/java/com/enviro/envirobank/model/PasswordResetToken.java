package com.enviro.envirobank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="password_reset_tokens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private Date createdAt;
    private Date expireAt;
    private Date confirmedAt;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private BankUser bankUser;

}
