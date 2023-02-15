package com.idle.idle_backend.mailsend.controller;

import com.idle.idle_backend.mailsend.dto.MailCheckRequest;
import com.idle.idle_backend.mailsend.service.MailSendService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class MailSendController {

    private final MailSendService emailService;
    @Operation(description = "메일 확인")
    @PostMapping("/api/login/mailConfirm")
    public String mailConfirm(@RequestBody MailCheckRequest emailDto) throws MessagingException, UnsupportedEncodingException {

        String authCode = emailService.sendEmail(emailDto.getEmail());

        return authCode;
    }
}