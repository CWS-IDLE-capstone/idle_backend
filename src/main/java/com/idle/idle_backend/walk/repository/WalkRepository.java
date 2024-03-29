package com.idle.idle_backend.walk.repository;

import com.idle.idle_backend.user.domain.User;
import com.idle.idle_backend.walk.entity.Walk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalkRepository extends JpaRepository<Walk, Long> {

    Walk findByUserAndStartTimeContains(User user, String startTime);

    List<Walk> findListByUserAndStartTimeContains(User user, String startTime);

    List<Walk> findListByUser(User user);

}
