package com.example.mybookservice.repository.User;

import com.example.mybookservice.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

  Long save(User user);

  User findById(Long id);

  List<User> findAll();

  Optional<User> findByLoginId(String loginId);

  // 회원 정보 수정
  // 회원 탈퇴
}
