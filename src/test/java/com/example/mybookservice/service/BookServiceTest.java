package com.example.mybookservice.service;

import com.example.mybookservice.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookServiceTest {

  @Autowired
  BookService bookService;

  @Test
  void save() { // save & find 둘다 테스트 코드
    Book book = new Book();
    bookService.save(book);
    Book findBook = bookService.findOne(book.getId());
    Assertions.assertThat(book).isEqualTo(findBook);
  }

  @Test
  void findAll() {
    Book book1 = new Book();
    Book book2 = new Book();
    bookService.save(book1);
    bookService.save(book2);
    List<Book> books = bookService.findAll();
    Assertions.assertThat(books.size()).isEqualTo(2);
  }

  @Test
  void delete() {
    Book book = new Book();
    bookService.delete(book.getId());
    List<Book> books = bookService.findAll();
    Assertions.assertThat(books.size()).isEqualTo(0);
  }
}