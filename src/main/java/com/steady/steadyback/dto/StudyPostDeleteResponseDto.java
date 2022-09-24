package com.steady.steadyback.dto;

import com.steady.steadyback.domain.StudyPost;
import com.steady.steadyback.domain.StudyPostImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyPostDeleteResponseDto {
    private Long studyPostId;
    String message;


    public StudyPostDeleteResponseDto(Long id, String message) {
        this.studyPostId=id;
        this.message=message;
    }
}
