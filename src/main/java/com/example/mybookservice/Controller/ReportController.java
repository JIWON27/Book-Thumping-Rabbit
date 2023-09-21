package com.example.mybookservice.Controller;

import com.example.mybookservice.domain.Report;
import com.example.mybookservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/report")
public class ReportController {

  private final ReportService reportService;

  // 독서록 폼 조회
  @GetMapping("/{reportId}/write")
  public String writeForm(@PathVariable Long reportId, Model model) {
    model.addAttribute("reportId", reportId);
    model.addAttribute("report", new Report());
    return "report/writeForm";
  }
  //독서록 등록
  @PostMapping("/{reportId}/write")
  public String addReport(@PathVariable Long reportId, @Validated @ModelAttribute Report report, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      // 에러를 가지고 있으면 다시 작성폼 보여주기
      return "report/writeForm";
    }
    if (report.getDate() == null) {
      report.setDate(LocalDate.now());
    }
    report.setId(reportId);
    reportService.save(report);
    return "redirect:/library";
  }
  // 독서록 조회
  @GetMapping("/{reportId}")
  public String report(@PathVariable Long reportId, Model model) {
    Report report = reportService.findOne(reportId);
    model.addAttribute("report", report);
    return "report/bookReport";
  }
  // 독서록 삭제
  @DeleteMapping("/{reportId}/delete")
  public String deleteReport(@PathVariable Long reportId){
    reportService.delete(reportId);
    return "redirect:/library";
  }

  // 업데이트 폼 보여주기
  @GetMapping("{reportId}/edit")
  public String updateForm(@PathVariable Long reportId, Model model){
    Report report = reportService.findOne(reportId);
    model.addAttribute("report", report);
    return "report/editForm";
  }

  @PostMapping("{reportId}/edit")
  public String update(@PathVariable Long reportId, @Validated @ModelAttribute Report report, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      // 에러를 가지고있으면 다시 수정 폼 보여주기
      return "report/editForm";
    }
    reportService.update(reportId, report);
    return "redirect:/library";
  }
}
