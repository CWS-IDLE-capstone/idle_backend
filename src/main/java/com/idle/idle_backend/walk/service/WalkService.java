package com.idle.idle_backend.walk.service;

import com.idle.idle_backend.user.domain.User;
import com.idle.idle_backend.user.domain.UserRepository;
import com.idle.idle_backend.walk.entity.Walk;
import com.idle.idle_backend.walk.repository.WalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class WalkService {

    private final WalkRepository walkRepository;
    private final UserRepository userRepository;

    public Long createWalk(Long userId, String routeImage, String startTime, String finishTime, String energyFinishTime, Float energyFinishDistance, Float distance) {
        //user 예외처리

        Optional<User> findUser = userRepository.findById(userId);
        User user = findUser.get();

        Walk walk = Walk.builder()
                .user(user)
                .routeImage(routeImage)
                .startTime(startTime)
                .finishTime(finishTime)
                .energyFinishTime(energyFinishTime)
                .energyFinishDistance(energyFinishDistance)
                .distance(distance)
                .build();

        walkRepository.save(walk);

        return walk.getId();
    }

    public Walk getWalk(Long userId, String date) {

        Optional<User> findUser = userRepository.findById(userId);
        User user = findUser.get();

        return walkRepository.findByUserAndStartTimeContains(user, date);
    }

    public List<Walk> getWalkList(Long userId, String walkMonth) {
        //user 예외처리

        Optional<User> findUser = userRepository.findById(userId);
        User user = findUser.get();

        List<Walk> listWalk = walkRepository.findListByUserAndStartTimeContains(user, walkMonth);


        return listWalk;

    }

    public void deleteWalk(Long walkId, Long longId) {
        //user 예외처리
        //walkId 예외처리

        walkRepository.deleteById(walkId);

    }

    public List<Walk> getWalkTotalList(Long userId) {

        //user 예외처리

        Optional<User> findUser = userRepository.findById(userId);
        User user = findUser.get();

        List<Walk> listWalk = walkRepository.findListByUser(user);

        return listWalk;
    }
}
