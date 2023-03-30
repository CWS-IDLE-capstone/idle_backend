package com.idle.idle_backend.walk.Dto;

import com.idle.idle_backend.walk.Entity.Walk;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
public class GetWalkListResponse {

    private List<Walk> walkList;

}
