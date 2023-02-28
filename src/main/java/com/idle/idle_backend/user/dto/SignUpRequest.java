package com.idle.idle_backend.user.dto;

import com.idle.idle_backend.user.enums.Providers;
import com.idle.idle_backend.user.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank
    private String name;

    @Schema(description = "이메일 형식으로 입력해주세요",
            example = "xxx1234@naver.com")
    @Email
    private String email;

    @NotBlank
    private String password;

    private String nickname;

    private Providers provider;

    private String providerId;

    private Sex sex;

}
