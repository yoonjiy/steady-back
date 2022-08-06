package com.steady.steadyback.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Entity
@NoArgsConstructor
@Getter
@Table(name = "study_post")
public class StudyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "study_id")
    private Study study;

    @Column(length = 100)
    private String link;

    @NotNull
    @Column
    @CreatedDate
    private LocalDateTime date;

    @PrePersist
    public void createDate(){
        this.date = LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime();;
    }   // local 시간으로 저장

    @Builder
    public StudyPost(Long id, User user, Study study, String link, LocalDateTime date) {
        this.id = id;
        this.user = user;
        this.study = study;
        this.link = link;
        this.date = date;
    }

}