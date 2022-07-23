package com.steady.steadyback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudyPostResponseDto {

    private Long studyPostId;

    private Long userId;

    private Long studyId;

    private String link;

    private List<String> imageUrl;

    private LocalDateTime date;

    private Integer nowFine;

    private String studyPostSort;

}

