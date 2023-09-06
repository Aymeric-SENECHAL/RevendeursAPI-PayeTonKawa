package com.payetonkawa.payetonkawa.QRCode;

import com.google.zxing.WriterException;
import com.payetonkawa.payetonkawa.customers.Customers;
import com.payetonkawa.payetonkawa.customers.CustomersRepository;
import com.payetonkawa.payetonkawa.products.SendEmailService;
import jakarta.mail.MessagingException;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
@Builder
public class QRCodeServiceImpl implements IQRCodeService{

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";
    private final CustomersRepository customersRepository;
    private static final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private SendEmailService sendEmailService;

    @Override
    public String getQRCode(String email, Model model) throws MessagingException{

        Optional<Customers> customers = customersRepository.findCustomersByEmail(email);
        String token = customers.get().getToken();
        if(token == null)
        {
            byte[] randomBytes = new byte[24];
            secureRandom.nextBytes(randomBytes);
            token = randomBytes.toString();
            customers.get().setToken(token);
            customersRepository.save(customers.get());
        }

        byte[] image = new byte[0];
        try {

            // Generate and Save Qr Code Image in static/image folder
            QRCodeGenerator.generateQRCodeImage(token,250,250,QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
            //e.printStackTrace();
        }

        model.addAttribute("token",token);

        sendEmailService.sendEmail(customers.get().getEmail(), "Bonjour, voici votre qrcode en P-J.", "QRCode pour votre connexion", QR_CODE_IMAGE_PATH);

        return "qrcode";
    }
}
