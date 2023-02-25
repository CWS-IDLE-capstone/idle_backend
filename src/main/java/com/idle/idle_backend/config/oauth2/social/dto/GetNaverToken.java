package com.idle.idle_backend.config.oauth2.social.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class GetNaverToken {

    @Schema(description = "네이버 서버로부터 받아온 인가코드",
            example = "인가코드")
    @NotBlank
    String code;

    @Schema(description = "상태값 일단 test",
            example = "test")
    @NotBlank
    String state;

}
