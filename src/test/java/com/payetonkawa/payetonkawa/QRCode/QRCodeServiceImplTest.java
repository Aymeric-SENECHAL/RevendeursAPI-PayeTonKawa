package com.payetonkawa.payetonkawa.QRCode;

import com.google.zxing.WriterException;
import com.payetonkawa.payetonkawa.customers.Customers;
import com.payetonkawa.payetonkawa.customers.CustomersRepository;
import com.payetonkawa.payetonkawa.products.SendEmailService;
import jakarta.mail.MessagingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QRCodeServiceImplTest {
    @Mock
    private CustomersRepository customersRepository;

    @Mock
    private SendEmailService sendEmailService;

    @Mock
    private Model model;

    @InjectMocks
    private QRCodeServiceImpl qrCodeService;

    private final String testEmail = "test@example.com";
    private final String testToken = "test_token";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetQRCode() throws MessagingException, WriterException, IOException {
        // Mocking the Customers object and its repository
        Customers mockCustomers = new Customers();
        mockCustomers.setEmail(testEmail);
        mockCustomers.setToken(null);

        when(customersRepository.findCustomersByEmail(testEmail)).thenReturn(Optional.of(mockCustomers));


        // Injecting the mock QRCodeGenerator into QRCodeServiceImpl
        QRCodeServiceImpl qrCodeServiceWithMockGenerator = new QRCodeServiceImpl(customersRepository, sendEmailService);

        doNothing().when(sendEmailService).sendEmail(anyString(), anyString(), anyString(), anyString());

        // Call the method to test
        String result = qrCodeServiceWithMockGenerator.getQRCode(testEmail, model);

        // Assertions
        assertEquals("qrcode", result);
        verify(customersRepository, times(1)).findCustomersByEmail(testEmail);
        verify(customersRepository, times(1)).save(mockCustomers);
        verify(sendEmailService, times(1)).sendEmail(testEmail, "tetsts", "Un test", "./src/main/resources/static/img/QRCode.png");
    }


    @Test
    void builder() {
        QRCodeServiceImpl.QRCodeServiceImplBuilder builder = QRCodeServiceImpl.builder();
        assertNotNull(builder);

        QRCodeServiceImpl qrCodeService = builder.build();
        assertNotNull(qrCodeService);
    }
}
