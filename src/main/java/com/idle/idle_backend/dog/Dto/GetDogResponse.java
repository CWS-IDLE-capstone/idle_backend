package com.idle.idle_backend.dog.Dto;

import com.idle.idle_backend.dog.Enum.Breed;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetDogResponse {

    private Long id;

    private Breed breed;

    private String imageUrl;

    private String dogName;

    private int age;

    @Builder
    public GetDogResponse(Long id, Breed breed, String imageUrl, String dogName, int age) {
        this.id = id;
        this.breed = breed;
        this.imageUrl = imageUrl;
        this.dogName = dogName;
        this.age = age;
    }
}
