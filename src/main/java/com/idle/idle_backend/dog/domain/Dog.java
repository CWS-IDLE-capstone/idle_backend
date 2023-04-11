package com.idle.idle_backend.dog.domain;

import com.idle.idle_backend.dog.enums.Breed;
import com.idle.idle_backend.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dog {

    @Id @GeneratedValue
    @Column(name = "DOG_ID")
    private Long id;

    private Breed breed;

    private String imageUrl;

    private String dogName;

    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Dog(Breed breed, String imageUrl, String dogName, int age) {
        this.breed = breed;
        this.imageUrl = imageUrl;
        this.dogName = dogName;
        this.age = age;
    }

    public void mappingUser(User user) {
        this.user = user;
        user.getDogList().add(this);
    }
}
