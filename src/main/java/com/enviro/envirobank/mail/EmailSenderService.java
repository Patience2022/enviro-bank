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

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class EmailSenderService implements EmailSender{

    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailSenderService.class);
    private final JavaMailSender javaMailSender;
    private final ThymeleafService thymeleafService;
    @Override
    public void sendResetPasswordLink(String name, String link, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setSubject("Reset Password");
            helper.setTo(email);
            helper.setFrom("patience@gmail.com");
            Map<String, Object>variables = new HashMap<>();
            variables.put("name", name);
            variables.put("link", link);

            helper.setText(thymeleafService.createContent("changePassword.html",variables), true);
            javaMailSender.send(mimeMessage);

        }catch (Exception e){
            throw new RuntimeException( e.getMessage());
        }
    }

    @Override
    public void sendPassword(String firstName, String password, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setSubject("Account confirmation");
            helper.setTo(email);
            helper.setFrom("patience@gmail.com");
            Map<String, Object>variables = new HashMap<>();
            variables.put("name", firstName);
            variables.put("pass", password);
            helper.setText(thymeleafService.createContent("sendPassword.html",variables), true);
            javaMailSender.send(mimeMessage);

        }catch (Exception e){
            throw new RuntimeException( e.getMessage());
        }

    }


}
