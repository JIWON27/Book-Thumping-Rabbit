package com.example.mybookservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

  private Long id; // 도서 id
  private String bookName; // 도서 이름
  private String author; // 도서 저자

  private Integer page; // 책 page 수 -> 진행도에 사용할
  private Integer curr_page; //현재 읽은 수
  private String progress; // 진행도

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate start; // 시작일
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate end; // 종료일

  private Status status; // 도서 현재 상태 [ ING 읽는 중, COMP 완독]록
  private Report report; //독서록

}