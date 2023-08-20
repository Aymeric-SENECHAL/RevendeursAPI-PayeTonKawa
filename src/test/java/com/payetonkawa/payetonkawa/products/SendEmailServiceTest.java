package com.payetonkawa.payetonkawa.products;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;
@PrepareForTest(SendEmailService.class)
class SendEmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private SendEmailService sendEmailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendEmail() throws Exception {
        String to = "baptiste@gmail.com";
        String body = "qrCode";
        String topic = "qrCode";
        String pathToAttachment = "src/main/resources/static.img/QRCode.png";

        MimeMessage mimeMessage = mock(MimeMessage.class);
        MimeMessageHelper mimeMessageHelper = mock(MimeMessageHelper.class);

        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        sendEmailService.sendEmail(to, body, topic, pathToAttachment);

        verify(mimeMessageHelper, times(1)).setFrom("gerard.mspr@gmail.com");
        verify(mimeMessageHelper, times(1)).setTo(to);
        verify(mimeMessageHelper, times(1)).setSubject(topic);
        verify(mimeMessageHelper, times(1)).setText(body);
        verify(mimeMessageHelper, times(1)).addAttachment("QRCode.png", new File(pathToAttachment));
        verify(javaMailSender, times(1)).send(mimeMessage);
    }
}
