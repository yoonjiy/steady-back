package com.steady.steadyback.service;

import com.steady.steadyback.domain.Notice;
import com.steady.steadyback.domain.NoticeRepository;
import com.steady.steadyback.domain.Study;
import com.steady.steadyback.domain.StudyRepository;
import com.steady.steadyback.dto.NoticeRequestDto;
import com.steady.steadyback.dto.NoticeResponseDto;
import com.steady.steadyback.util.errorutil.CustomException;
import com.steady.steadyback.util.errorutil.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final StudyRepository studyRepository;

    public NoticeResponseDto createNotice(NoticeRequestDto noticeRequestDto) {

        Study study = noticeRequestDto.getStudy();

        studyRepository.findById(study.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_NOT_FOUND));

        Notice notice = noticeRepository.save(noticeRequestDto.toEntity());

        NoticeResponseDto noticeResponseDto = new NoticeResponseDto(notice.getId(), notice.getStudy().getId(), notice.getContent());

        return noticeResponseDto;
    }
}
