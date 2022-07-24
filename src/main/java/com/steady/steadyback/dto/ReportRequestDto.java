package com.steady.steadyback.dto;

import com.steady.steadyback.domain.Report;
import com.steady.steadyback.domain.StudyPost;
import com.steady.steadyback.domain.User;
import com.steady.steadyback.domain.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequestDto {
    private Long studyPostId;
}
