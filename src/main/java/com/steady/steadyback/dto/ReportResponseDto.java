package com.steady.steadyback.dto;

import com.steady.steadyback.domain.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReportResponseDto {
    private Long reportId;
    private Long reporterId;
    private String reporterName;
    private Long violatorId;
    private String violatorName;
    private Long studyPostId;
    private LocalDateTime date;

    public ReportResponseDto(Report report) {
        this.reportId = report.getId();
        this.reporterId = report.getUser().getId();
        this.reporterName = report.getUser().getNickname();
        this.violatorId = report.getStudyPost().getUser().getId();
        this.violatorName = report.getStudyPost().getUser().getNickname();
        this.studyPostId = report.getStudyPost().getId();
        this.date = report.getDate();
    }

    @Builder
    public ReportResponseDto(Long reportId, Long reporterId, String reporterName, Long violatorId, String violatorName, Long studyPostId, LocalDateTime date) {
        this.reportId = reportId;
        this.reporterId = reporterId;
        this.reporterName = reporterName;
        this.violatorId = violatorId;
        this.violatorName = violatorName;
        this.studyPostId = studyPostId;
        this.date = date;
    }
}
