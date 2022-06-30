package com.steady.steadyback.service;

import com.steady.steadyback.domain.Study;
import com.steady.steadyback.domain.StudyRepository;
import com.steady.steadyback.dto.StudyRequestDto;
import com.steady.steadyback.dto.StudyResponseDto;
import com.steady.steadyback.util.errorutil.CustomException;
import com.steady.steadyback.util.errorutil.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyService {
    private final StudyRepository studyRepository;

    public StudyResponseDto findStudyById(Long id) {
        Study study = studyRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_NOT_FOUND));

        return new StudyResponseDto(study);
    }

    public Long createStudy(StudyRequestDto studyRequestDto) {
        if(studyRequestDto.getName().isEmpty() || studyRequestDto.getAccount().isEmpty()
                || studyRequestDto.getTime()==null || studyRequestDto.getMoney()==null || studyRequestDto.getLateMoney()==null) {
            throw new CustomException(ErrorCode.CANNOT_EMPTY_CONTENT);
        }

        if(studyRequestDto.getMoney() < studyRequestDto.getLateMoney()) {
            throw new CustomException(ErrorCode.BIGGER_LATE_MONEY);
        }

        Study study = studyRequestDto.toEntity();
        studyRepository.save(study);

        return study.getId();
    }
}
