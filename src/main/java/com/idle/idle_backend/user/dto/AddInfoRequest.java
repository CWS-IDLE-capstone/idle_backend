package com.idle.idle_backend.user.dto;

import com.idle.idle_backend.user.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddInfoRequest {

    private String name;

    private String nickname;

    private String imageUrl;

    private String location;

    private Sex sex;


}

