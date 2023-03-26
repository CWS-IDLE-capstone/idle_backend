package com.idle.idle_backend.walk.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateWalkRequest {

    private String routeImage;

    private Float distance;

    private LocalDateTime startTime;

    private LocalDateTime finishTime;

    private LocalDateTime energyFinishTime;

    private Long energyFinishDistance;

    public CreateWalkRequest(String routeImage, Float distance, LocalDateTime startTime, LocalDateTime finishTime, LocalDateTime energyFinishTime, Long energyFinishDistance) {
        this.routeImage = routeImage;
        this.distance = distance;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.energyFinishTime = energyFinishTime;
        this.energyFinishDistance = energyFinishDistance;
    }
}
