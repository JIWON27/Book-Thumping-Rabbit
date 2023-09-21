package com.example.mybookservice.domain;

public enum Status {
  ING("읽는 중"), COMP("완독");

  private final String description;

  Status(String description) {
    this.description = description;
  }
  public String getDescription() {
    return description;
  }
}
