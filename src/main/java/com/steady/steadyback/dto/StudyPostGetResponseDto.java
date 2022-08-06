package com.steady.steadyback.dto;

import com.steady.steadyback.domain.StudyPost;
import com.steady.steadyback.domain.StudyPostImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudyPostGetResponseDto {

    private Long studyPostId;

    private Long userId;
    private String userName;
    private String userNickname;
    private Long studyId;

    private String link;

    private LocalDateTime date;

    //private StudyPostImageResponseDto studyPostImageResponseDto;

    private Long studyPostImageId;
    private String studyPostImageURL;

    public StudyPostGetResponseDto(StudyPost studyPost) {
        this.studyPostId= studyPost.getId();
        this.userId= studyPost.getUser().getId();
        this.studyId=studyPost.getStudy().getId();
        this.link= studyPost.getLink();
        this.date= studyPost.getDate();
        this.userId= studyPost.getUser().getId();
        this.userName = studyPost.getUser().getName();
        this.userNickname = studyPost.getUser().getNickname();
    }

    public StudyPostGetResponseDto(StudyPost studyPost, StudyPostImageResponseDto studyPostImageResponseDto) {
        this.studyPostId= studyPost.getId();
        this.userId= studyPost.getUser().getId();
        this.userName = studyPost.getUser().getName();
        this.userNickname = studyPost.getUser().getNickname();
        this.studyId=studyPost.getStudy().getId();
        this.link= studyPost.getLink();
        this.date= studyPost.getDate();
        //this.studyPostImageResponseDto= studyPostImageResponseDto;
        this.studyPostImageId= studyPostImageResponseDto.getStudyPostImageId();
        this.studyPostImageURL= studyPostImageResponseDto.getImageUrl();
    }

}