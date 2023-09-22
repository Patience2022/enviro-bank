package com.enviro.envirobank.mail;

public interface EmailSender {
    void sendEmail(String to, String email);
    String buildEmail(String name, String link,String email);
    void sendNewEmail(String name,String to, String email);

}
