package com.idle.idle_backend.mailSend.controller;

import com.idle.idle_backend.mailSend.dto.MailCheckRequest;
import com.idle.idle_backend.mailSend.service.MailSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class MailSendController {

    private final MailSendService emailService;

    @PostMapping("login/mailConfirm")
    public String mailConfirm(@RequestBody MailCheckRequest emailDto) throws MessagingException, UnsupportedEncodingException {

        String authCode = emailService.sendEmail(emailDto.getEmail());

        return authCode;
    }
}