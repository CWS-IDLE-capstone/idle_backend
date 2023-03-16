package com.idle.idle_backend.user.dto;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@NoArgsConstructor
public class NormalLoginResponse {

    private String email;

    private String name;
    private String token;

    @Builder
    public NormalLoginResponse(String token, String email, String name){
        this.token = token;
        this.email = email;
        this.name = name;
    }
}
