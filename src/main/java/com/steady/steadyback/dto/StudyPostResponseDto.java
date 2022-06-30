package com.steady.steadyback.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyPostResponseDto {
    Long studyId;
    String message;

    public StudyPostResponseDto(Long studyId, String message) {
        this.studyId = studyId;
        this.message = message;
    }
}
