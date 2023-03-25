package com.idle.idle_backend.dog.Repository;

import com.idle.idle_backend.dog.Domain.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
}
