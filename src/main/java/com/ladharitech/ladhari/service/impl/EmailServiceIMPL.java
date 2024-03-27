package com.ladharitech.ladhari.service.impl;

import com.ladharitech.ladhari.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceIMPL implements EmailService {

    private final JavaMailSender javaMailSender;
    @Async
    public void sendEmail(SimpleMailMessage email){
        javaMailSender.send(email);
    }

}
