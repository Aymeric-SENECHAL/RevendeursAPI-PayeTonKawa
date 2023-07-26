package com.payetonkawa.payetonkawa;

import com.payetonkawa.payetonkawa.products.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PayetonkawaApplication {
	public static void main(String[] args) {SpringApplication.run(PayetonkawaApplication.class, args);	}

}
