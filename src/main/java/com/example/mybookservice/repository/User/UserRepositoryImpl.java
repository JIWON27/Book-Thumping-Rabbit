package com.example.mybookservice.repository.User;

import com.example.mybookservice.domain.User;
import com.example.mybookservice.repository.User.UserRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private static Map<Long, User> store = new HashMap<>();
  private static long sequence = 0L;

  @Override
  public Long save(User user) {
    user.setId(++sequence);
    store.put(user.getId(), user);
    return user.getId();
  }

  @Override
  public User findById(Long id) {
    User user = store.get(id);
    return user;
  }

  @Override
  public List<User> findAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public Optional<User> findByLoginId(String loginId) {
    List<User> Users = findAll();
    for (User user : Users) {
      if (user.getLoginId().equals(loginId)){
        return Optional.of(user);
      }
    }
    return Optional.empty();
  }
}
