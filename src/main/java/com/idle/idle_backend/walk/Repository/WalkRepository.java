package com.idle.idle_backend.walk.Repository;

import com.idle.idle_backend.walk.Entity.Walk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkRepository extends JpaRepository<Walk, Long> {
}
