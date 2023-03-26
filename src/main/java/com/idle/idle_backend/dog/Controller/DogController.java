package com.idle.idle_backend.dog.Controller;


import com.idle.idle_backend.dog.Domain.Dog;
import com.idle.idle_backend.dog.Dto.CreateDogRequest;
import com.idle.idle_backend.dog.Dto.CreateDogResponse;
import com.idle.idle_backend.dog.Dto.GetDogResponse;
import com.idle.idle_backend.dog.Service.DogService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/dog")
@RestController
public class DogController {

    private final DogService dogService;

    @PostMapping("")
    public ResponseEntity<CreateDogResponse> createDog(@RequestBody CreateDogRequest createDogRequest, @RequestAttribute Claims claims) {
        Integer userId = (int) claims.get("userId");
        Long longId = Long.valueOf(userId);

        Long dogId = dogService.createDog(longId, createDogRequest.getDogName(), createDogRequest.getAge(), createDogRequest.getBreed(), createDogRequest.getImageUrl());

        String message = "강아지 생성에 성공하였습니다.";

        return new ResponseEntity<>(new CreateDogResponse(message, dogId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<GetDogResponse>> getDogs(@RequestAttribute Claims claims) {
        Integer userId = (int) claims.get("userId");
        Long longId = Long.valueOf(userId);

        List<Dog> dogList = dogService.getDogs(longId);

        List<GetDogResponse> result = new ArrayList<>();

        for (Dog dog : dogList) {
            result.add(GetDogResponse.builder()
                    .id(dog.getId())
                    .dogName(dog.getDogName())
                    .age(dog.getAge())
                    .breed(dog.getBreed())
                    .imageUrl(dog.getImageUrl())
                    .build());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{dogId}")
    public ResponseEntity<GetDogResponse> getDog(@PathVariable Long dogId, @RequestAttribute Claims claims) {
        Integer userId = (int) claims.get("userId");
        Long longId = Long.valueOf(userId);

        Dog dog = dogService.getDog(longId, dogId);

        GetDogResponse getDogResponse = GetDogResponse.builder()
                .id(dog.getId())
                .dogName(dog.getDogName())
                .age(dog.getAge())
                .breed(dog.getBreed())
                .imageUrl(dog.getImageUrl())
                .build();

        return new ResponseEntity(getDogResponse, HttpStatus.OK);

    }

    @DeleteMapping("/{dogId}")
    public ResponseEntity deleteDog(@PathVariable Long dogId, @RequestAttribute Claims claims) {
        Integer userId = (int) claims.get("userId");
        Long longId = Long.valueOf(userId);

        dogService.deleteDog(userId, dogId);

        return new ResponseEntity(HttpStatus.OK);
    }


}
