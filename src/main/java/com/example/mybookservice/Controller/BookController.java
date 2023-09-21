package com.example.mybookservice.Controller;

import com.example.mybookservice.domain.Report;
import com.example.mybookservice.domain.User;
import com.example.mybookservice.dto.BookUpdateDto;
import com.example.mybookservice.domain.Book;
import com.example.mybookservice.domain.Status;
import com.example.mybookservice.service.BookService;
import com.example.mybookservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller //Spring MVC 패턴을 사용
@RequestMapping("/library")
@RequiredArgsConstructor
@Slf4j
public class BookController {

  private final BookService bookService;
  private final ReportService reportService;

  // 도서 추가하는 폼 보여주기
  @GetMapping("/add")
  public String addForm(Model model) {
    model.addAttribute("book", new Book());
    return "book/addForm";
  }
  // 도서 생성
  @PostMapping("/add")
  public String addBook(@ModelAttribute Book book, BindingResult bindingResult){
    if (!StringUtils.hasText(book.getBookName())) {
      bindingResult.rejectValue("bookName", "required", null);
    }
    if (!StringUtils.hasText(book.getAuthor())) {
      bindingResult.rejectValue("author", "required", null);
    }
    if (book.getStatus() == null) {
      bindingResult.addError(new FieldError("book", "status", "하나를 선택해주세요."));
    }
    //global Error
    if (book.getPage() != null && book.getCurr_page() != null) {
      if (book.getPage() < book.getCurr_page()){
        bindingResult.reject("page_error",new Object[]{book.getPage()},null);
      }
    }
    if (book.getStart() == null) {
      book.setStart(LocalDate.now());
    }
    if (book.getPage() == book.getCurr_page()) {
      book.setStatus(Status.COMP);
    }
    if (bindingResult.hasErrors()) {
      // 에러를 가지고있으면 다시 입력폼 보여주기
      return "book/addForm";
    }
    bookService.save(book);
    return "redirect:/library";
  }

  // 도서 단건 조회
  @GetMapping("/book/{bookId}")
  public String book(@PathVariable Long bookId, Model model) {
    Book book = bookService.findOne(bookId);
    model.addAttribute("book", book);
    return "book/book";
  }

  // 도서 전체 조회
  @GetMapping()
  public String books(@SessionAttribute(name="loginUser", required = false) User loginUser, Model model) {
    if (loginUser == null) {
      return "redirect:/";
    }
    model.addAttribute("loginUser", loginUser);
    List<Book> books = bookService.findAll();

    for (Book book : books) {
      //book.setProgress((int)(((double)book.getCurr_page() / book.getPage()) * 100)+"%");
      Report report = reportService.findOne(book.getId());
      book.setReport(report);
    }
    model.addAttribute("books", books);
    return "book/books";
  }
  // 도서 삭제
  @GetMapping("/{bookId}/delete")
  public String deleteBook(@PathVariable Long bookId){
    bookService.delete(bookId);
    return "redirect:/library";
  }
  // 도서 업데이트 폼 보여주기
  @GetMapping("/{bookId}/edit")
  public String editForm(@PathVariable Long bookId, Model model) {
    Book book = bookService.findOne(bookId);

    BookUpdateDto bookUpdateDto = new BookUpdateDto();
    bookUpdateDto.setBookName(book.getBookName());
    bookUpdateDto.setAuthor(book.getAuthor());
    bookUpdateDto.setCurr_page(book.getCurr_page());

    model.addAttribute("bookUpdateDto", bookUpdateDto);
    return "book/editForm";
  }

  // 도서 업데이트 하기
  @PostMapping("/{BookId}/edit")
  public String editBook(@PathVariable Long BookId, @ModelAttribute BookUpdateDto bookUpdateDto, BindingResult bindingResult) {
    if (!StringUtils.hasText(bookUpdateDto.getBookName())) {
      bindingResult.rejectValue("bookName", "required", null);
    }
    if (!StringUtils.hasText(bookUpdateDto.getAuthor())) {
      bindingResult.rejectValue("author", "required", null);
    }
    Book book = bookService.findOne(BookId);
    if (book.getPage() != null && bookUpdateDto.getCurr_page() != null) {
      if (book.getPage() < bookUpdateDto.getCurr_page()) {
        bindingResult.reject("page_error", new Object[]{book.getPage()},null);
      }
    }
    if (bindingResult.hasErrors()) {
      return "book/editForm";
    }
    bookService.update(BookId, bookUpdateDto);
    return "redirect:/library";
  }

  @ModelAttribute("status") // 컨트롤러에서 정의한 메서드는 해당 컨트롤러 내에서 반환된 데이터를 모든 뷰에서 공유할 수 있음.
  public Status[]  status(){
    Status[] values = Status.values(); // enum안에 있는 것들을 배열로 넘겨줌.
    return values;
  }
}
