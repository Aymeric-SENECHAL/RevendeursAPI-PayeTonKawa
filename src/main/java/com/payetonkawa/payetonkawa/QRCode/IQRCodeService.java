package com.payetonkawa.payetonkawa.QRCode;

import jakarta.mail.MessagingException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

public interface IQRCodeService {

   public String getQRCode(String email, Model model) throws MessagingException;
}
