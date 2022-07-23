package com.steady.steadyback.dto;

import com.steady.steadyback.domain.StudyPost;
import com.steady.steadyback.domain.StudyPostImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudyPostResponseDto {

    private Long studyPostId;

    private Long userId;

    private Long studyId;

    private String link;

    private LocalDate date;

    private StudyPostImageResponseDto studyPostImageResponseDto;

    public StudyPostResponseDto(StudyPost studyPost) {
        this.studyPostId= studyPost.getId();
        this.userId= studyPost.getUser().getId();
        this.studyId=studyPost.getStudy().getId();
        this.link= studyPost.getLink();
        this.date= studyPost.getDate();
    }

    public StudyPostResponseDto(StudyPost studyPost, StudyPostImageResponseDto studyPostImageResponseDto) {
        this.studyPostId= studyPost.getId();
        this.userId= studyPost.getUser().getId();
        this.studyId=studyPost.getStudy().getId();
        this.link= studyPost.getLink();
        this.date= studyPost.getDate();
        this.studyPostImageResponseDto= studyPostImageResponseDto;
    }

}
