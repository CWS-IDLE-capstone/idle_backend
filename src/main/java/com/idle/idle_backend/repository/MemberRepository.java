package com.idle.idle_backend.repository;

import com.idle.idle_backend.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원이 저장소에 저장, findById, Name 으로 찾아올수있
    Optional<Member> findById(Long id); //Optional 은 findBy 로 가져오는 값이 없으면 null 을 반환하는데 null 을 그대로 반환하는대신
    Optional<Member> findByName(String name); // Optional 이라는 걸로 감싸서 반환
    List<Member> findAll(); //findAll 지금까지 저장된 모든 회원 리스트 반환
}
