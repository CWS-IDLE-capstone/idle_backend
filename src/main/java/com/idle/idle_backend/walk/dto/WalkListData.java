package com.idle.idle_backend.walk.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WalkListData {

    private String startTime;

    private String endTime;

    private Float distance;

    @Builder
    public WalkListData(String startTime, String endTime, Float distance) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.distance = distance;
    }
}
