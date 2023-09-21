package com.example.mybookservice.repository.Reposrt;

import com.example.mybookservice.domain.Report;
import com.example.mybookservice.repository.Reposrt.ReportRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ReportRepositoryImpl implements ReportRepository {
  private static Map<Long, Report> repository = new HashMap<>();

  @Override
  public Long save(Report report) {
    repository.put(report.getId(), report);
    return report.getId();
  }

  @Override
  public Report findOne(Long id) {
    return repository.get(id);
  }

  @Override
  public void delete(Long id) {
    repository.remove(id);
  }

  @Override
  public void update(Long id, Report updateReport) {
    // 업데이트.
  }
}
