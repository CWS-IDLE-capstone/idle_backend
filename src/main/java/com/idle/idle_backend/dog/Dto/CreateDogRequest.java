package com.idle.idle_backend.dog.Dto;

import com.idle.idle_backend.dog.Enum.Breed;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateDogRequest {

    private Breed breed;

    private String imageUrl;

    private String dogName;

    private int age;

    @Builder
    public CreateDogRequest(Breed breed, String imageUrl, String dogName, int age) {
        this.breed = breed;
        this.imageUrl = imageUrl;
        this.dogName = dogName;
        this.age = age;
    }
}
