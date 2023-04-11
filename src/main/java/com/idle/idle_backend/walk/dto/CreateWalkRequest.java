package com.idle.idle_backend.walk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateWalkRequest {

    private String routeImage;

    private Float distance;

    private String startTime;

    private String finishTime;

    private String energyFinishTime;

    private Float energyFinishDistance;

    public CreateWalkRequest(String routeImage, Float distance, String startTime, String finishTime, String energyFinishTime, Float energyFinishDistance) {
        this.routeImage = routeImage;
        this.distance = distance;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.energyFinishTime = energyFinishTime;
        this.energyFinishDistance = energyFinishDistance;
    }
}
