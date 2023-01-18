package com.idle.idle_backend.config.oauth2.social.controller;

import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class NaverController {

    private final NaverService naverService;


    @PostMapping("/naver")
    public ResponseEntity<GetLoginTokenResponse> getNaverToken(@Valid @RequestBody GetnaverToken getnaverToken) throws IOException, ParseException {
        String code = getnaverToken.getCode();
        String state = getnaverToken.getState();
        GetLoginTokenResponse getLoginTokenResponse = naverService.NaverLogin(code, state);
        return new ResponseEntity(getLoginTokenResponse, HttpStatus.OK);
    }

}