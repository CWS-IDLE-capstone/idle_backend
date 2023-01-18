package com.idle.idle_backend.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long Id);
    Boolean existsByProviderId(String providerId);

    Optional<User> findByProviderId(String providerId);

}
