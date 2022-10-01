package com.steady.steadyback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyPostCheckResponseDto {
    private int totalNum;
    private int checkNum;

    public StudyPostCheckResponseDto(int totalNum, int checkNum) {
        this.totalNum=totalNum;
        this.checkNum=checkNum;
    }
}
