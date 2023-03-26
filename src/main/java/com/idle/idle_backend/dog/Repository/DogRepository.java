package com.idle.idle_backend.dog.Repository;

import com.idle.idle_backend.dog.Domain.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findDogByUser(Long userId);

    Dog findDogById(Long dogId);
}
