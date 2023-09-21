package com.example.mybookservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class User {

  private Long id; // db 식별자

  @NotBlank(message = "아이디를 입력해주세요.")
  private String loginId; // 로그인 아이디

  @NotBlank(message = "이름을 입력해주세요.")
  private String name; // 사용자 이름

  @NotBlank(message = "비밀번호를 입력해주세요.")
  private String password; // 비밀 번호


}
