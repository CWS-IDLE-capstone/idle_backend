package com.idle.idle_backend.user.service;

import com.idle.idle_backend.config.jwt.JwtTokenProvider;
import com.idle.idle_backend.user.domain.User;
import com.idle.idle_backend.user.domain.UserRepository;
import com.idle.idle_backend.user.dto.AddInfoRequest;
import com.idle.idle_backend.user.dto.JwtRequestDto;
import com.idle.idle_backend.user.dto.NormalLoginResponse;
import com.idle.idle_backend.user.dto.SignUpRequest;
import javassist.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtProvider;

    public Long registerUser(SignUpRequest signUpRequestDto) throws Exception{

        User user = User.builder()
                .name(signUpRequestDto.getName())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .email(signUpRequestDto.getEmail())
                .provider(signUpRequestDto.getProvider())
                .providerId(signUpRequestDto.getProviderId())
                .nickname(signUpRequestDto.getNickname())
                .sex(signUpRequestDto.getSex())
                .build();

        userRepository.save(user);
        return user.getId();
    }

    public void addUserInfo(AddInfoRequest addInfoRequest, Long userId) throws NotFoundException {
        Optional<User> findUser = userRepository.findById(userId);
        if (findUser.isEmpty()) {
            throw new NotFoundException("해당 아이디 값을 가진 유저가 없습니다. 아이디를 다시 한번 확인하세요.");
        }

        User user = findUser.get();

        user.setName(addInfoRequest.getName());
        user.setNickname(addInfoRequest.getNickname());
        user.setImageUrl(addInfoRequest.getImageUrl());
        user.setLocation(addInfoRequest.getLocation());
        user.setSex(addInfoRequest.getSex());
        user.setNickname(addInfoRequest.getNickname());

        userRepository.save(user);

    }

    public NormalLoginResponse login(JwtRequestDto request) throws Exception {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new BadCredentialsException("잘못된 계정정보입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }

        return NormalLoginResponse.builder()
                .token(jwtProvider.makeJwtToken(user.getId(),30))
                .email(user.getEmail())
                .name(user.getName())
                .build();

    }

    public User getUser(Long longId) {
        Optional<User> user = userRepository.findById(longId);
        User byId = user.get();

        return byId;
    }
}
