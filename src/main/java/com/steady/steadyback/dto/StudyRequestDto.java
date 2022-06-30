package com.steady.steadyback.dto;

import com.steady.steadyback.domain.Study;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyRequestDto {
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

    @Builder
    public StudyRequestDto(Study study) {
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
    }

    public Study toEntity() {
        return Study.builder()
                .name(name)
                .description(description)
                .mon(mon)
                .tue(tue)
                .wed(wed)
                .thu(thu)
                .fri(fri)
                .sat(sat)
                .sun(sun)
                .account(account)
                .time(time)
                .money(money)
                .lateMoney(lateMoney)
                .peopleCount(peopleCount)
                .build();
    }
}
