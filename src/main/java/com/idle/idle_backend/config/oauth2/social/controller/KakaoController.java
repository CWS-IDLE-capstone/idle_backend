package com.idle.idle_backend.config.oauth2.social.controller;

import com.idle.idle_backend.config.oauth2.social.dto.GetKakaoTokenRequest;
import com.idle.idle_backend.config.oauth2.social.dto.GetLoginTokenResponse;
import com.idle.idle_backend.config.oauth2.social.dto.GetUserInfoResponse;
import com.idle.idle_backend.config.oauth2.social.service.KakaoService;
import com.idle.idle_backend.user.domain.User;
import com.idle.idle_backend.user.domain.UserRepository;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class KakaoController {

    private final KakaoService kakaoService;
    private final UserRepository userRepository;

    /**
     * 카카오 서버에서 유저정보 조희
     *
     * @param getKakaoToken
     * @return 엑세스 토큰
     * @throws IOException
     * @throws ParseException
     */

    @Operation(summary = "카카오 로그인" , description = "카카오 로그인")
    @PostMapping("/kakao")
    public ResponseEntity<GetLoginTokenResponse> getKaKaoToken(@Valid @RequestBody GetKakaoTokenRequest getKakaoToken) throws IOException, ParseException {
        String code = getKakaoToken.getCode();
        String redirectUrl = getKakaoToken.getRedirectUrl();
        GetLoginTokenResponse getLoginToken = kakaoService.KakaoLogin(code, redirectUrl);
        return new ResponseEntity(getLoginToken, HttpStatus.OK);
    }

    @Operation(summary = "일반 로그인" , description = "일반 로그인")
    @GetMapping("/login")
    public ResponseEntity<GetUserInfoResponse> getUserInfo(@RequestAttribute Claims claims) {
        //엑세스 토큰안의 유저 아이디로 유저를 찾은 다음 유저정보 리턴해줌
        Integer userId = (int) claims.get("userId");
        Long longId = Long.valueOf(userId);
        //userId로 유저 꺼내기
        Optional<User> result = userRepository.findById(longId);
        User user = result.get();

        GetUserInfoResponse getUserInfo = GetUserInfoResponse.builder()
                .username(user.getNickname())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .provider(user.getProvider())
                .userId(user.getId())
                .message("유저 정보 요청에 성공했습니다.")
                .build();

        return new ResponseEntity(getUserInfo, HttpStatus.OK);
    }
}
