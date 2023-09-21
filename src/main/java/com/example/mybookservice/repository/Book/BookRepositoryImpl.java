package com.example.mybookservice.repository.Book;

import com.example.mybookservice.dto.BookUpdateDto;
import com.example.mybookservice.domain.Book;
import com.example.mybookservice.repository.Book.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository {

  // 우선 인메모리 방식을 사용하고 HashMap 사용
  private static Map<Long, Book> repository = new HashMap<>();
  private static long id = 0L;
  @Override
  public Long save(Book book) {
    book.setId(++id);
    repository.put(book.getId(), book);
    return book.getId();
  }

  @Override
  public Book findOne(Long id) {
    return repository.get(id);
  }
  @Override
  public List<Book> findAll() {
    ArrayList<Book> books = new ArrayList<>(repository.values());
    return books;
  }

  @Override
  public void delete(Long id) {
    repository.remove(id);
  }

  @Override
  public void update() {
    // 북 업데이트
  }
}
