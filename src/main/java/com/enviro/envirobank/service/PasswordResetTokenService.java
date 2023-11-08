package com.enviro.envirobank.service;

import com.enviro.envirobank.model.BankUser;
import com.enviro.envirobank.model.PasswordResetToken;

import java.util.Optional;

public interface PasswordResetTokenService {
    PasswordResetToken getToken(String token);
    void deleteToken(String token);
    PasswordResetToken createNewToken(PasswordResetToken token);

    PasswordResetToken getTokenByUser(BankUser bankUser);
}
