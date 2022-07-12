package com.steady.steadyback.service;

import com.steady.steadyback.domain.*;
import com.steady.steadyback.dto.ReportRequestDto;
import com.steady.steadyback.dto.ReportResponseDto;
import com.steady.steadyback.dto.StudyGetResponseDto;
import com.steady.steadyback.util.errorutil.CustomException;
import com.steady.steadyback.util.errorutil.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final StudyPostRepository studyPostRepository;
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final UserStudyRepository userStudyRepository;

    public ReportResponseDto createReport(ReportRequestDto reportRequestDto) {
        StudyPost studyPost = reportRequestDto.getStudyPost();
        studyPostRepository.findById(studyPost.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_POST_NOT_FOUND));

        User user = reportRequestDto.getUser();
        studyPostRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        Report report = reportRepository.save(reportRequestDto.toEntity());

        ReportResponseDto reportResponseDto = new ReportResponseDto(report.getId(), report.getUser().getId(), report.getStudyPost().getId());

        return reportResponseDto;
    }

    public List<ReportResponseDto> findReportListByStudyId(Long studyId) {
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_NOT_FOUND));

        return reportRepository.findAllByStudyPost_Study(study)
                .stream()
                .map(report -> new ReportResponseDto(report))
                .collect(Collectors.toList());
    }

    public List<ReportResponseDto> findReportList() {
        return reportRepository.findAll()
                .stream()
                .map(report -> new ReportResponseDto(report))
                .collect(Collectors.toList());
    }

    public ReportResponseDto findReportById(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_NOT_FOUND));

        ReportResponseDto reportResponseDto = new ReportResponseDto(report);
        return reportResponseDto;
    }

    public String deleteReportById(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_NOT_FOUND));

        reportRepository.deleteById(reportId);
        return "SUCCESS";
    }

    public String cancelReportById(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_NOT_FOUND));

        User canceledUser = report.getStudyPost().getUser();
        Study study = report.getStudyPost().getStudy();

        UserStudy userStudy = userStudyRepository.findByUserAndStudy(canceledUser, study);
        userStudy.addNowFine();
        userStudyRepository.save(userStudy);

        reportRepository.deleteById(reportId);
        return userStudy.getNowFine().toString() + "원으로 변경";
    }
}
