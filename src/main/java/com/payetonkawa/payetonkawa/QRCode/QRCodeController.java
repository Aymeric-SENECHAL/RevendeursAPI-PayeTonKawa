package com.payetonkawa.payetonkawa.QRCode;

import jakarta.mail.MessagingException;
import lombok.Builder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@Builder
@RequestMapping("/api/v1/")
public class QRCodeController {

    private IQRCodeService qrCodeService;

    @GetMapping("/qrCode")
    public String getQRCode(@RequestParam String email, Model model) throws MessagingException {
        return qrCodeService.getQRCode(email, model);
    }
}
