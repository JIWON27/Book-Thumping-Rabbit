package com.example.mybookservice.service;

import com.example.mybookservice.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final UserService userService;
  public User login(String loginId, String password) {
    Optional<User> findUserByLoginID = userService.findByLoginId(loginId);
    User user = findUserByLoginID.get();
    if (user.getPassword().equals(password)) {
      // 로그인 성공
      return user;
    } else{
      // 로그인 실패
      return null;
    }
  }
}
