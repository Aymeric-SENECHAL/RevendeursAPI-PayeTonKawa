package com.payetonkawa.payetonkawa.products;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String body, String topic,String pathToAttachment) throws MessagingException {
        System.out.println("sending email");
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("gerard.mspr@gmail.com");
        helper.setTo(to);
        helper.setSubject(topic);
        helper.setText(body);
        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("QRCode.png", file);
        javaMailSender.send(message);
        System.out.println("Sent email...");
    }
}
