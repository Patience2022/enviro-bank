package com.enviro.envirobank.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailSenderService implements EmailSender{

    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailSenderService.class);
    private final JavaMailSender javaMailSender;
    @Override
    @Async
    public void sendEmail(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm email address");
            helper.setFrom("patcele87@gmail.com");
            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException me){
            LOGGER.error("Failed to send email ",me);
            throw new IllegalStateException("Failed to send email");
        }
    }

    public String buildEmail(String name, String link, String password){
   return "<div " +
                "            <p >Hi " + name + "," +
           "</p><p > Thank you for registering." + "<br/>"+
           " Please click on the below link to activate your account: " +
           "Please reset your password. Temp password is:  "+password+". Please use your email address as username"+
           " </p><p > <a href=\"" + link + "\">Activate Now</a> </p> Link will expire in 24 hours. <p>See you soon</p>" +

                "</div></div>";
    }

    @Override
    public void sendNewEmail(String name, String to, String email) {

    }

}
