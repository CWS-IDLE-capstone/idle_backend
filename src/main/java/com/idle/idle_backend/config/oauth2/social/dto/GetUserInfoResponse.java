package com.idle.idle_backend.config.oauth2.social.dto;

import com.idle.idle_backend.user.enums.Providers;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetUserInfoResponse implements Serializable {

    private Long userId;

    /**
     * 유저 종류 - KAKAO, NAVER, GOOGLE
     */
    private Providers provider;

    /**
     * Oauth 에서 제공하는 아이디
     */
    private String providerId;

    private String username;

    private String nickname;

    private String email;

    private String imageUrl;

    private String message;

    @Builder
    public GetUserInfoResponse(Long userId, Providers provider, String providerId, String username, String nickname, String email, String imageUrl, String message) {
        this.userId = userId;
        this.provider = provider;
        this.providerId = providerId;
        this.username = username;
        this.email = email;
        this.imageUrl = imageUrl;
        this.message = message;
    }
}
