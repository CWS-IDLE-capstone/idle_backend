package com.idle.idle_backend.dog.Controller;


import com.idle.idle_backend.dog.Dto.CreateDogRequest;
import com.idle.idle_backend.dog.Dto.CreateDogResponse;
import com.idle.idle_backend.dog.Service.DogService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/dog")
@RestController
public class DogController {

    private final DogService dogService;

    @PostMapping("")
    public ResponseEntity<CreateDogResponse> createDog(@RequestBody CreateDogRequest createDogRequest, @RequestAttribute Claims claims){
        Integer userId = (int) claims.get("userId");
        Long longId = Long.valueOf(userId);

        Long dogId = dogService.createDog(longId, createDogRequest.getDogName(), createDogRequest.getAge(), createDogRequest.getBreed(), createDogRequest.getImageUrl());

        String message = "강아지 생성에 성공하였습니다.";

        return new ResponseEntity<>(new CreateDogResponse(message,dogId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<GetDogResponse> getDog(@RequestBody @RequestAttribute Claims claims)


}
