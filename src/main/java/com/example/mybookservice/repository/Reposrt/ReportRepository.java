package com.example.mybookservice.repository.Reposrt;

import com.example.mybookservice.domain.Report;

public interface ReportRepository {
  // 헷갈리는게 그거다 Book id를 사용해야할꺼같은데,,
  // 독후감 저장
  Long save(Report report);

  // 단건 조회 use id
  Report findOne(Long id);

  // 독후감 삭제
  void delete(Long id);

  // 독후감 수정
  // 수정할 책의 id와 수정정보를 담은 updateReport 객체를 파라미터로 받음
  void update(Long id, Report editReportDto);

}
