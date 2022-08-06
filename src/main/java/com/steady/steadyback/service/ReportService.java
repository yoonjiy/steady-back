package com.steady.steadyback.service;

import com.steady.steadyback.domain.*;
import com.steady.steadyback.dto.ReportRequestDto;
import com.steady.steadyback.dto.ReportResponseDto;
import com.steady.steadyback.dto.StudyGetResponseDto;
import com.steady.steadyback.util.errorutil.CustomException;
import com.steady.steadyback.util.errorutil.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public ReportResponseDto createReport(ReportRequestDto reportRequestDto, User user) {
        StudyPost studyPost = studyPostRepository.findById(reportRequestDto.getStudyPostId())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_POST_NOT_FOUND));

        UserStudy userStudy = userStudyRepository.findByUserAndStudy(user, studyPost.getStudy())
                .orElseThrow(() -> new CustomException(ErrorCode.INFO_NOT_FOUNT));

        Report report = reportRepository.save(Report.builder()
                .user(user)
                .studyPost(studyPost)
                .build()
        );

        ReportResponseDto reportResponseDto = ReportResponseDto.builder()
                .reportId(report.getId())
                .reporterId(user.getId())
                .reporterName(user.getNickname())
                .violatorId(studyPost.getUser().getId())
                .violatorName(studyPost.getUser().getNickname())
                .studyPostId(studyPost.getId())
                .date(report.getDate())
                .build();

        return reportResponseDto;
    }

    public List<ReportResponseDto> findReportListByStudyId(Long studyId, User user) {
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_NOT_FOUND));

        if(!userStudyRepository.existsByUserAndStudyAndLeaderIsTrue(user, study)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
        }

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

    public ReportResponseDto findReportById(Long reportId, User user) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_NOT_FOUND));

        Study study = report.getStudyPost().getStudy();

        if(!userStudyRepository.existsByUserAndStudyAndLeaderIsTrue(user, study)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
        }

        ReportResponseDto reportResponseDto = new ReportResponseDto(report);
        return reportResponseDto;
    }

    @Transactional
    public String deleteReportById(Long reportId, User user) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_NOT_FOUND));

        Study study = report.getStudyPost().getStudy();

        if(!userStudyRepository.existsByUserAndStudyAndLeaderIsTrue(user, study)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
        }

        reportRepository.deleteById(reportId);
        return "SUCCESS";
    }

    @Transactional
    public String cancelReportById(Long reportId, User user) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_NOT_FOUND));

        Study study = report.getStudyPost().getStudy();

        if(!userStudyRepository.existsByUserAndStudyAndLeaderIsTrue(user, study)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
        }

        User canceledUser = report.getStudyPost().getUser();

        UserStudy userStudy = userStudyRepository.findByUserAndStudy(canceledUser, study)
                .orElseThrow(() -> new CustomException(ErrorCode.INFO_NOT_FOUNT));

        userStudy.addNowFine();
        userStudyRepository.save(userStudy);

        reportRepository.deleteById(reportId);
        return userStudy.getNowFine().toString() + "원으로 변경";
    }
}
