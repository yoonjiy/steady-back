package com.steady.steadyback.dto;

import com.steady.steadyback.domain.Study;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudyGetResponseDto {
    private Long studyId;
    private String name;
    private String uuid;
    private String summary;
    private String description;
    private Boolean mon;
    private Boolean tue;
    private Boolean wed;
    private Boolean thu;
    private Boolean fri;
    private Boolean sat;
    private Boolean sun;
    private String accountBank;
    private String account;
    private String accountName;
    private Integer hour;
    private Integer minute;
    private Integer money;
    private Integer lateMoney;
    private Integer peopleCount;

    public StudyGetResponseDto(Study study) {
        this.studyId = study.getId();
        this.name = study.getName();
        this.uuid = study.getUuid();
        this.summary = study.getSummary();
        this.description = study.getDescription();
        this.mon = study.getMon();
        this.tue = study.getTue();
        this.wed = study.getWed();
        this.thu = study.getThu();
        this.fri = study.getFri();
        this.sat = study.getSat();
        this.sun = study.getSun();
        this.accountBank = study.getAccountBank();
        this.account = study.getAccount();
        this.accountName = study.getAccountName();
        this.hour = study.getHour();
        this.minute = study.getMinute();
        this.money = study.getMoney();
        this.lateMoney = study.getLateMoney();
        this.peopleCount = study.getPeopleCount();
    }

    public Study toEntity() {
        return Study.builder()
                .id(this.studyId)
                .name(this.name)
                .uuid(this.uuid)
                .summary(this.summary)
                .description(this.description)
                .mon(this.mon)
                .tue(this.tue)
                .wed(this.wed)
                .thu(this.thu)
                .fri(this.fri)
                .sat(this.sat)
                .sun(this.sun)
                .accountBank(this.accountBank)
                .account(this.account)
                .accountName(this.accountName)
                .hour(this.hour)
                .minute(this.minute)
                .money(this.money)
                .lateMoney(this.lateMoney)
                .peopleCount(this.peopleCount)
                .build();
    }
}
