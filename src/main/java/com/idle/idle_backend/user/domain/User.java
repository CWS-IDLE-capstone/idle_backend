package com.idle.idle_backend.user.domain;

import com.idle.idle_backend.user.enums.Providers;
import com.idle.idle_backend.user.enums.Sex;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    private String password;

    @Column(name = "USER_NAME")
    private String name;

    private String nickname;

    private String email;

    private String imageUrl;

    private String location;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private Providers provider;    // oauth2를 이용할 경우 어떤 플랫폼을 이용하는지

    private String providerId;  // oauth2를 이용할 경우 아이디값

}
