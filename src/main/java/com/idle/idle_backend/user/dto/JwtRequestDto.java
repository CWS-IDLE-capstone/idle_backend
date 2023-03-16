package com.idle.idle_backend.user.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JwtRequestDto {

    private String email;

    private String password;

    public JwtRequestDto(String email, String password){
        this.email = email;
        this.password = password;
    }
}
