package com.steady.steadyback.controller;

import com.steady.steadyback.domain.User;
import com.steady.steadyback.dto.ReportRequestDto;
import com.steady.steadyback.dto.ReportResponseDto;
import com.steady.steadyback.dto.StudyResponseDto;
import com.steady.steadyback.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportResponseDto> createReport(@RequestBody ReportRequestDto reportRequestDto, @AuthenticationPrincipal User user) {
        ReportResponseDto reportResponseDto = reportService.createReport(reportRequestDto, user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reportResponseDto.getReportId())
                .toUri();

        return ResponseEntity.created(location).body(reportResponseDto);
    }

    @GetMapping("/studies/{studyId}")
    public List<ReportResponseDto> getReportListByStudyId(@PathVariable Long studyId, @AuthenticationPrincipal User user) {
        return reportService.findReportListByStudyId(studyId, user);
    }

    @GetMapping
    public List<ReportResponseDto> getReportList() {
        return reportService.findReportList();
    }

    @GetMapping("/{reportId}")
    public ReportResponseDto getReportById(@PathVariable Long reportId, @AuthenticationPrincipal User user) {
        return reportService.findReportById(reportId, user);
    }

    @DeleteMapping("/{reportId}")
    public String deleteReportById(@PathVariable Long reportId, @AuthenticationPrincipal User user) {
        return reportService.deleteReportById(reportId, user);
    }

    @PutMapping("/{reportId}/confirm")
    public String cancelPost(@PathVariable Long reportId, @AuthenticationPrincipal User user) {
        return reportService.cancelReportById(reportId, user);
    }
}
