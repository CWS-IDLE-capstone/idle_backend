package com.idle.idle_backend.config.oauth2.social.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class GetNaverToken {
    @NotBlank
    String code;

    @NotBlank
    String state;

}
