package com.example.mybookservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BookUpdateDto {
  private String bookName;
  private String author;
  private Integer curr_page; //현재 읽은 수

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate end; // 종료일
}
