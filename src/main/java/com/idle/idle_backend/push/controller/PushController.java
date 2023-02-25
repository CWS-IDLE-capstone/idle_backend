package com.idle.idle_backend.push.controller;

import com.idle.idle_backend.push.dto.RequestDTO;
import com.idle.idle_backend.push.service.FirebaseCloudMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PushController {

    private final FirebaseCloudMessageService firebaseCloudMessageService;


    @Operation(summary = "푸시 메시지" , description = "푸시 알림 메시지")
    @ApiResponses({
            @ApiResponse(responseCode = "200" , description = "OK!"),
            @ApiResponse(responseCode = "400" , description = "Bad Request"),
            @ApiResponse(responseCode = "404" , description = "NOT FOUND"),
            @ApiResponse(responseCode = "500" , description = "INTERNAL SERVER ERROR!!")
    })
    @PostMapping("/api/fcm")
    public ResponseEntity pushMessage(@RequestBody RequestDTO requestDTO) throws IOException {
        System.out.println(requestDTO.getTargetToken() + " "
                +requestDTO.getTitle() + " " + requestDTO.getBody());

        firebaseCloudMessageService.sendMessageTo(
                requestDTO.getTargetToken(),
                requestDTO.getTitle(),
                requestDTO.getBody());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/fcm/topic")
    public ResponseEntity pushMessages(@RequestBody RequestDTO requestDTO) throws IOException {
        System.out.println(requestDTO.getTargetToken() + " "
                +requestDTO.getTitle() + " " + requestDTO.getBody());

        firebaseCloudMessageService.sendMessagesTo(
                requestDTO.getTargetToken(),
                requestDTO.getTitle(),
                requestDTO.getBody());

        return ResponseEntity.ok().build();
    }
}
