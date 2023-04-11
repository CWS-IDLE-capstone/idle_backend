package com.idle.idle_backend.walk.controller;

import com.idle.idle_backend.walk.dto.CreateWalkRequest;
import com.idle.idle_backend.walk.dto.GetWalkListResponse;
import com.idle.idle_backend.walk.dto.GetWalkResponse;
import com.idle.idle_backend.walk.entity.Walk;
import com.idle.idle_backend.walk.service.WalkService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/walk")
@RequiredArgsConstructor
@RestController
@Tag(name = "walk", description = "산책 api")
public class WalkController {

    private final WalkService walkService;

    @Operation(summary = "산책 생성", description = "헤더에 토큰, 바디에 {routeImage, distance, startTime, finishTime, energyFinishTime, energyFinishDistance} json 형식으로 보내주시면 됩니다.")
    @PostMapping("")
    public ResponseEntity<Long> createWalk(@RequestBody CreateWalkRequest createWalkRequest, @RequestAttribute Claims claims){
        Integer userId = (int) claims.get("userId");
        Long longId = Long.valueOf(userId);

        Long walkId = walkService.createWalk(longId, createWalkRequest.getRouteImage(), createWalkRequest.getStartTime(),
                createWalkRequest.getFinishTime(), createWalkRequest.getEnergyFinishTime(), createWalkRequest.getEnergyFinishDistance(),
                createWalkRequest.getDistance());

        return new ResponseEntity<>(walkId, HttpStatus.OK);
    }

    @Operation(summary = "산책 날짜별 단건 조회", description = "헤더에 토큰, 파라미터에 년,월,일,시,분,초 보내주시면 됩니다.")
    @GetMapping("/{walkDate}")
    public ResponseEntity<GetWalkResponse> getWalk(@PathVariable String walkDate, @RequestAttribute Claims claims){
        Integer userId = (int) claims.get("userId");
        Long longId = Long.valueOf(userId);

        Walk walk = walkService.getWalk(longId, walkDate);

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

    @Operation(summary = "월별 산책 리스트 조회", description = "헤더에 토큰, 파라미터에 년, 월 보내주시면 됩니다")
    @GetMapping("/{walkMonth}/list")
    public ResponseEntity<GetWalkListResponse> getWalkList(@PathVariable String walkMonth, @RequestAttribute Claims claims) {
        Integer userId = (int) claims.get("userId");
        Long longId = Long.valueOf(userId);

        List<Walk> listWalk = walkService.getWalkList(longId, walkMonth);

        GetWalkListResponse getWalkListResponse = GetWalkListResponse.builder()
                .walkList(listWalk)
                .build();
        
        
        return new ResponseEntity(getWalkListResponse, HttpStatus.OK);

    }

    @Operation(summary = "산책 삭제", description = "파라미터에 삭제할 walkId 넣어주시면 됩니다")
    @DeleteMapping("/{walkId}")
    public ResponseEntity<Long> deleteWalk(@PathVariable Long walkId, @RequestAttribute Claims claims){

        Integer userId = (int) claims.get("userId");
        Long longId = Long.valueOf(userId);

        walkService.deleteWalk(walkId, longId);

        return new ResponseEntity<>(walkId, HttpStatus.OK);
    }



}
