package com.idle.idle_backend.config.oauth2.social.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetLoginTokenResponse {

    private String accessToken;

    private boolean isNewUser;

    @Builder
    public GetLoginTokenResponse(String accessToken, boolean isNewUser) {
        this.accessToken = accessToken;
        this.isNewUser = isNewUser;
    }
}