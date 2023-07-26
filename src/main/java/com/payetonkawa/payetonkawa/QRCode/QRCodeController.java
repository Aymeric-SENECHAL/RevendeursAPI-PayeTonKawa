package com.payetonkawa.payetonkawa.QRCode;

import com.google.zxing.WriterException;
import com.payetonkawa.payetonkawa.customers.Customers;
import com.payetonkawa.payetonkawa.customers.CustomersRepository;
import com.payetonkawa.payetonkawa.products.SendEmailService;
import jakarta.mail.MessagingException;
import lombok.Builder;
import org.springdoc.core.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@CrossOrigin()
@RestController
@Builder
@RequestMapping("/api/v1/")
public class QRCodeController {

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

    private final CustomersRepository customersRepository;

    @Autowired
    private SendEmailService sendEmailService;

    @GetMapping("/")
    public String getQRCode( @RequestBody String email, Model model) throws MessagingException {
        String medium="https://rahul26021999.medium.com/";
        Optional<Customers> customers = customersRepository.findByEmail(email);
        String token = customers.get().getToken();

        byte[] image = new byte[0];
        try {

            // Generate and Return Qr Code in Byte Array
            image = QRCodeGenerator.getQRCodeImage(medium,250,250);

            // Generate and Save Qr Code Image in static/image folder
            QRCodeGenerator.generateQRCodeImage(token,250,250,QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        model.addAttribute("medium",medium);
        model.addAttribute("github",token);
        model.addAttribute("qrcode",qrcode);

        sendEmailService.sendEmail(customers.get().getEmail(), "tetsts", "Un test", QR_CODE_IMAGE_PATH);

        return "qrcode";
    }
}
