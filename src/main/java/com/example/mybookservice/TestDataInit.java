package com.example.mybookservice;

import com.example.mybookservice.domain.Book;
import com.example.mybookservice.domain.Status;
import com.example.mybookservice.domain.User;
import com.example.mybookservice.repository.Book.BookRepository;
import com.example.mybookservice.repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Member;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TestDataInit {

  private final BookRepository BookRepository;
  private final UserRepository userRepository;

  /**
   * 테스트용 데이터 추가
   */
  @PostConstruct
  public void init() {
    BookRepository.save(new Book(1L, "예시 타이틀", "저자명", 100, 70, null, LocalDate.now(),null, Status.ING,null ));
    BookRepository.save(new Book(2L, "예시 타이틀", "저자명", 100, 90, null,LocalDate.now(),null,Status.ING,null ));
    BookRepository.save(new Book(3L, "예시 타이틀", "저자명", 100, 34, null,LocalDate.now(),null,Status.ING,null ));
    BookRepository.save(new Book(4L, "예시 타이틀", "저자명", 100, 100, null,LocalDate.now(),null,Status.COMP,null ));
    BookRepository.save(new Book(5L, "예시 타이틀", "저자명", 100, 100, null,LocalDate.now(),null,Status.COMP,null ));

    User user = new User();
    user.setLoginId("test");
    user.setPassword("test!");
    user.setName("테스터");

    userRepository.save(user);
  }

}
