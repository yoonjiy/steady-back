package com.steady.steadyback.dto;

import com.steady.steadyback.domain.Study;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudyResponseDto {
    private Long studyId;
    private String name;
    private String description;
    private Boolean mon;
    private Boolean tue;
    private Boolean wed;
    private Boolean thu;
    private Boolean fri;
    private Boolean sat;
    private Boolean sun;
    private String account;
    private Integer time;
    private Integer money;
    private Integer lateMoney;
    private Integer peopleCount;

    public StudyResponseDto(Study study) {
        this.studyId = study.getId();
        this.name = study.getName();
        this.description = study.getDescription();
        this.mon = study.getMon();
        this.tue = study.getTue();
        this.wed = study.getWed();
        this.thu = study.getThu();
        this.fri = study.getFri();
        this.sat = study.getSat();
        this.sun = study.getSun();
        this.account = study.getAccount();
        this.time = study.getTime();
        this.money = study.getMoney();
        this.lateMoney = study.getLateMoney();
        this.peopleCount = study.getPeopleCount();
    }
}
