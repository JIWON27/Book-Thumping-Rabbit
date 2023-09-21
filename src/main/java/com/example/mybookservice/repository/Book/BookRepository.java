package com.example.mybookservice.repository.Book;

import com.example.mybookservice.domain.Book;

import java.util.List;

public interface BookRepository {

  // 책 저장
  Long save(Book book);

  // 책 단건 조회 use id
  Book findOne(Long id);

  // 책 전체 조회
  List<Book> findAll();

  // 책 삭제
  void delete(Long id);

  // 책 수정
  // 수정할 책의 id와 수정정보를 담은 updateBook 객체를 파라미터로 받음
  void update();
}
