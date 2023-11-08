package com.enviro.envirobank.mail;

public interface EmailSender {


    void sendPassword(String firstName, String password, String email);
    void sendResetPasswordLink(String name, String link, String email);
}
