package com.idle.idle_backend.walk.dto;

import com.idle.idle_backend.walk.entity.Walk;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetWalkListResponse {

    private List<Walk> walkList;

}
