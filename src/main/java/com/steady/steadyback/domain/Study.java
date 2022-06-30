package com.steady.steadyback.domain;

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

    @Column(length = 100)
    String description;

    @NotNull
//    @ColumnDefault("0")
//    @Column(columnDefinition = "TINYINT", length = 1)
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

    @NotNull
    @Column(length = 20)
    String account;

    @NotNull
    @Column
    Integer time;

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
    public Study(Long id, String name, String description, Boolean mon, Boolean tue, Boolean wed, Boolean thu, Boolean fri, Boolean sat, Boolean sun, String account, Integer time, Integer money, Integer lateMoney, Integer peopleCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.account = account;
        this.time = time;
        this.money = money;
        this.lateMoney = lateMoney;
        this.peopleCount = peopleCount;
    }
}
