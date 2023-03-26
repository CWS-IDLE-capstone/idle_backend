package com.idle.idle_backend.walk.Controller;


import com.idle.idle_backend.walk.Dto.CreateWalkRequest;
import com.idle.idle_backend.walk.Dto.CreateWalkReponse;
import com.idle.idle_backend.walk.Dto.GetWalkResponse;
import com.idle.idle_backend.walk.Entity.Walk;
import com.idle.idle_backend.walk.Service.WalkService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/walk")
@RequiredArgsConstructor
@RestController
public class WalkController {

    private final WalkService walkService;



    @PostMapping("")
    public ResponseEntity<Long> createWalk(@RequestBody CreateWalkRequest createWalkRequest, @RequestAttribute Claims claims){
        Integer userId = (int) claims.get("userId");
        Long longId = Long.valueOf(userId);

        Long walkId = walkService.createWalk(longId, createWalkRequest.getRouteImage(), createWalkRequest.getStartTime(),
                createWalkRequest.getFinishTime(), createWalkRequest.getEnergyFinishTime(), createWalkRequest.getEnergyFinishDistance(),
                createWalkRequest.getDistance());

        return new ResponseEntity<>(walkId, HttpStatus.OK);
    }

    @GetMapping("/{walkId}")
    public ResponseEntity<GetWalkResponse> getWalk(@PathVariable Long walkId, @RequestAttribute Claims claims){
        Integer userId = (int) claims.get("userId");
        Long longId = Long.valueOf(userId);

        Walk walk = walkService.getWalk(longId, walkId);

        GetWalkResponse getWalkResponse = GetWalkResponse.builder()
                .routeImage(walk.getRouteImage())
                .startTime(walk.getStartTime())
                .distance(walk.getDistance())
                .finishTime(walk.getFinishTime())
                .energyFinishTime(walk.getEnergyFinishTime())
                .energyFinishDistance(walk.getEnergyFinishDistance())
                .build();

        return new ResponseEntity(getWalkResponse, HttpStatus.OK);

    }

}
