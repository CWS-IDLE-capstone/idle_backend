package com.idle.idle_backend.walk.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetWalkResponse {

    private String routeImage;

    private Float distance;

    private String startTime;

    private String finishTime;

    private String energyFinishTime;

    private Long energyFinishDistance;


    @Builder
    public GetWalkResponse(String routeImage, String startTime, String finishTime, String energyFinishTime, Long energyFinishDistance, Float distance) {
        this.routeImage = routeImage;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.energyFinishTime = energyFinishTime;
        this.energyFinishDistance = energyFinishDistance;
        this.distance = distance;
    }
}
