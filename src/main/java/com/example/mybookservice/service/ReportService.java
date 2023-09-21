package com.example.mybookservice.service;

import com.example.mybookservice.domain.Report;
import com.example.mybookservice.repository.Reposrt.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

  private final ReportRepository repository;

  public Long save(Report report) {
    repository.save(report);
    return report.getId();
  }

  public Report findOne(Long id) {
    return repository.findOne(id);
  }

  public void delete(Long id) {
    repository.delete(id);
  }
  public void update(Long id, Report updateReport) {
    Report report = repository.findOne(id);
    report.setTitle(updateReport.getTitle());
    report.setContent(updateReport.getContent());
    report.setDate(updateReport.getDate());
    repository.update(id, updateReport);

  }
}
