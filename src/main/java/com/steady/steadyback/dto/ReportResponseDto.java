package com.steady.steadyback.dto;

import com.steady.steadyback.domain.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDto {
    private Long reportId;
    private Long userId;
    private Long studyPostId;

    public ReportResponseDto(Report report) {
        this.reportId = report.getId();
        this.userId = report.getUser().getId();
        this.studyPostId = report.getStudyPost().getId();
    }
}
