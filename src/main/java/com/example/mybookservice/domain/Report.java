package com.example.mybookservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Report {

  private Long id;

  @NotBlank(message = "제목을 입력해주세요")
  private String title; // 독서록 제목

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate date; // 독서록

  @NotBlank(message = "내용을 작성해주세요.")
  private String content; // 독후감 내용

}
