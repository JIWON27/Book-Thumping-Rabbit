package com.example.mybookservice.service;

import com.example.mybookservice.domain.Status;
import com.example.mybookservice.dto.BookUpdateDto;
import com.example.mybookservice.domain.Book;
import com.example.mybookservice.repository.Book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor // 생성자가 1개면 @Autowired 생략가능
public class BookService {

  private final BookRepository bookRepository;
  // 북 저장
  public Long save(Book book){
    bookRepository.save(book);
    return book.getId();
  }
  // 북 단건 조회
  public Book findOne(Long id) {
    Book book = bookRepository.findOne(id);
    return book;
  }
  // 북 전체 조회
  public List<Book> findAll(){
    List<Book> books = bookRepository.findAll();
    return books;
  }
  // 북 삭제
  public void delete(Long id){
    bookRepository.delete(id);
  }
  // 북 업데이트
  public void update(Long id, BookUpdateDto updateBook){
    Book originBook = bookRepository.findOne(id);
    if (updateBook.getCurr_page() == originBook.getPage() || updateBook.getEnd() != null) {
      updateBook.setCurr_page(originBook.getPage());
      updateBook.setEnd(LocalDate.now());
      originBook.setStatus(Status.COMP);
    } else{
      originBook.setEnd(updateBook.getEnd());

    }
    originBook.setBookName(updateBook.getBookName());
    originBook.setAuthor(updateBook.getAuthor());
    originBook.setCurr_page(updateBook.getCurr_page());
    originBook.setEnd(updateBook.getEnd());
    bookRepository.update();
  }
}
