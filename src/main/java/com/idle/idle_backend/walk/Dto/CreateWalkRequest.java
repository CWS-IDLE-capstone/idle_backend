package com.idle.idle_backend.walk.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateWalkRequest {

    private String routeImage;

    private Float distance;

    private String startTime;

    private String finishTime;

    private String energyFinishTime;

    private Long energyFinishDistance;

    public CreateWalkRequest(String routeImage, Float distance, String startTime, String finishTime, String energyFinishTime, Long energyFinishDistance) {
        this.routeImage = routeImage;
        this.distance = distance;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.energyFinishTime = energyFinishTime;
        this.energyFinishDistance = energyFinishDistance;
    }
}
