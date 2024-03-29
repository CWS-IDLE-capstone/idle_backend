package com.idle.idle_backend.config.oauth2.social.controller;



import com.idle.idle_backend.config.oauth2.social.dto.GetLoginTokenResponse;
import com.idle.idle_backend.config.oauth2.social.dto.GetNaverToken;
import com.idle.idle_backend.config.oauth2.social.service.NaverService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class NaverController {

    private final NaverService naverService;

    @Operation(summary = "네이버 로그인" , description = "네이버 로그인")
    @PostMapping("/naver")
    public ResponseEntity<GetLoginTokenResponse> getNaverToken(@Valid @RequestBody GetNaverToken getnaverToken) throws IOException, ParseException {
        String code = getnaverToken.getCode();
        String state = getnaverToken.getState();
        GetLoginTokenResponse getLoginTokenResponse = naverService.NaverLogin(code, state);
        return new ResponseEntity(getLoginTokenResponse, HttpStatus.OK);
    }

}
