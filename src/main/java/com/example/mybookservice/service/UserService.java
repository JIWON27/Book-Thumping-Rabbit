package com.example.mybookservice.service;

import com.example.mybookservice.domain.User;
import com.example.mybookservice.repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  // 회원가입(생성)
  public Long save(User user){
    Long saveUserId = userRepository.save(user);
    return saveUserId;
  }
  // 회원 조회
  public User findById(Long id){
    User user = userRepository.findById(id);
    return user;
  }
  public List<User> findAll() {
    List<User> users = userRepository.findAll();
    return users;
  }
  public Optional<User> findByLoginId(String loginId){
    Optional<User> user = userRepository.findByLoginId(loginId);
    return user;
  }
  // 회원 정보 수정
  // 회원 탈퇴(삭제)
}
