package com.idle.idle_backend.user.controller;

import com.idle.idle_backend.common.CommonResponse;

import com.idle.idle_backend.user.dto.AddInfoRequest;
import com.idle.idle_backend.user.dto.SignUpRequest;
import com.idle.idle_backend.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "일반 회원가입" , description = "일반 회원가입")
    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequestDto) throws Exception {
        userService.registerUser(signUpRequestDto);
        CommonResponse response = new CommonResponse("회원가입에 성공했습니다.");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Operation(summary = "회원 정보 입력" , description = "회원 정보 입력")
    @PatchMapping("/addInfo")
    public ResponseEntity<CommonResponse> addUserInfo(@Valid @RequestBody AddInfoRequest addInfoRequest, @RequestAttribute Claims claims) {
        Integer userId = (int) claims.get("userId");
        Long longId = Long.valueOf(userId);
        try {
            userService.addUserInfo(addInfoRequest, longId);
        } catch (NotFoundException e) {
            CommonResponse response = new CommonResponse("해당 아이디 값을 가진 유저가 없습니다. 아이디를 다시 한번 확인하세요.");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        CommonResponse response = new CommonResponse("유저 정보 추가에 성공했습니다.");
        return new ResponseEntity(response, HttpStatus.OK);
    }

}