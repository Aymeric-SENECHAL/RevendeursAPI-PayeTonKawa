package com.payetonkawa.payetonkawa.QRCode;

import com.google.zxing.WriterException;
import com.payetonkawa.payetonkawa.customers.Customers;
import com.payetonkawa.payetonkawa.customers.CustomersRepository;
import com.payetonkawa.payetonkawa.products.SendEmailService;
import jakarta.mail.MessagingException;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@CrossOrigin()
@RestController
@Builder
@RequestMapping("/api/v1/")
public class QRCodeController {

    private IQRCodeService qrCodeService;

    @GetMapping("/")
    public String getQRCode( @RequestParam String email, Model model) throws MessagingException {
        return qrCodeService.getQRCode(email, model);
    }
}
