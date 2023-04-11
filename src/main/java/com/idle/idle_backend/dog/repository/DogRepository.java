package com.idle.idle_backend.dog.repository;

import com.idle.idle_backend.dog.domain.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findDogByUser(Long userId);

    Dog findDogById(Long dogId);
}
