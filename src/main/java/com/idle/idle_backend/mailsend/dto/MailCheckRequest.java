package com.idle.idle_backend.mailsend.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailCheckRequest {

    @NotEmpty(message = "이메일을 입력해주세요")
    public String email;

}
