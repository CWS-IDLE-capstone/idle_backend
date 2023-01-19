package com.idle.idle_backend.user.service;

import com.idle.idle_backend.user.domain.User;
import com.idle.idle_backend.user.domain.UserRepository;
import com.idle.idle_backend.user.dto.AddInfoRequest;
import com.idle.idle_backend.user.dto.SignUpRequest;
import javassist.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    public Long registerUser(SignUpRequest signUpRequestDto) throws Exception{
        if (userRepository.existsByProviderId(signUpRequestDto.getProviderId())) {
            throw new Exception("중복된 유저가 존재합니다.");
        }

        User user = new User();


        User result = userRepository.save(user);
        return result.getId();
    }

    public void addUserInfo(AddInfoRequest addInfoRequest, Long userId) throws NotFoundException {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isEmpty()) {
            throw new NotFoundException("해당 아이디 값을 가진 유저가 없습니다. 아이디를 다시 한번 확인하세요.");
        }

        User user = byId.get();

        user.setNickname(addInfoRequest.getNickname());

        userRepository.save(user);

    }
}
