package com.steady.steadyback.domain;

import com.steady.steadyback.dto.StudyGetResponseDto;
import com.steady.steadyback.dto.StudyRequestDto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@DynamicInsert
@Table(name = "study")
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(length = 20)
    String name;

    @NotNull
    @Column(length = 200)
    String uuid;

    @Column(length = 100)
    String description;

    @NotNull
    @Column
    Boolean mon;

    @NotNull
    @Column
    Boolean tue;

    @NotNull
    @Column
    Boolean wed;

    @NotNull
    @Column
    Boolean thu;

    @NotNull
    @Column
    Boolean fri;

    @NotNull
    @Column
    Boolean sat;

    @NotNull
    @Column
    Boolean sun;

    @Column(length = 20)
    String account;

    @NotNull
    @Column
    Integer hour;

    @NotNull
    @Column
    Integer minute;

    @NotNull
    @Column
    Integer money;

    @NotNull
    @Column(name = "latemoney")
    Integer lateMoney;

    @NotNull
    @Column(name = "people_count")
    Integer peopleCount;

    @Builder
    public Study(Long id, String name, String uuid, String description, Boolean mon, Boolean tue, Boolean wed, Boolean thu, Boolean fri, Boolean sat, Boolean sun, String account, Integer hour, Integer minute, Integer money, Integer lateMoney, Integer peopleCount) {
        this.id = id;
        this.name = name;
        this.uuid = uuid;
        this.description = description;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.account = account;
        this.hour = hour;
        this.minute = minute;
        this.money = money;
        this.lateMoney = lateMoney;
        this.peopleCount = peopleCount;
    }

    public void updateRule(StudyRequestDto studyRequestDto) {
        this.name = studyRequestDto.getName();
        this.description = studyRequestDto.getDescription();
        this.mon = studyRequestDto.getMon();
        this.tue = studyRequestDto.getTue();
        this.wed = studyRequestDto.getWed();
        this.thu = studyRequestDto.getThu();
        this.fri = studyRequestDto.getFri();
        this.sat = studyRequestDto.getSat();
        this.sun = studyRequestDto.getSun();
        this.account = studyRequestDto.getAccount();
        this.hour = studyRequestDto.getHour();
        this.minute = studyRequestDto.getMinute();
        this.money = studyRequestDto.getMoney();
        this.lateMoney = studyRequestDto.getLateMoney();
    }
}
