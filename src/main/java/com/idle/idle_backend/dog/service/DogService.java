package com.idle.idle_backend.dog.service;


import com.idle.idle_backend.dog.domain.Dog;
import com.idle.idle_backend.dog.enums.Breed;
import com.idle.idle_backend.dog.repository.DogRepository;
import com.idle.idle_backend.user.domain.User;
import com.idle.idle_backend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class DogService {

    private final DogRepository dogRepository;
    private final UserRepository userRepository;



    public Long createDog(Long userId, String dogName, int age, Breed breed, String imageUrl) {

        Optional<User> byId = userRepository.findById(userId);
        User user = byId.get();

        Dog dog = Dog.builder()
                .age(age)
                .breed(breed)
                .dogName(dogName)
                .imageUrl(imageUrl)
                .build();

        dog.mappingUser(user);

        dogRepository.save(dog);
        
        return dog.getId();
    }

    public List<Dog> getDogs(Long longId) {
        Optional<User> byId = userRepository.findById(longId);
        User user = byId.get();

        return dogRepository.findDogByUser(user.getId());
    }

    public Dog getDog(Long longId, Long dogId) {
        //유저 예외 처리

        return dogRepository.findDogById(dogId);
    }

    public void deleteDog(Integer userId, Long dogId) {

        dogRepository.deleteById(dogId);
    }

}
