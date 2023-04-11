package com.idle.idle_backend.dog.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateDogResponse {
    private String message;

    private Long dogId;

    public CreateDogResponse(String message, Long dogId) {
        this.message = message;
        this.dogId = dogId;
    }
}
