package com.steady.steadyback.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "study_post_id")
    private StudyPost studyPost;

    @Column
    private LocalDateTime date;

    @PrePersist
    public void createDate(){
        this.date = LocalDateTime.now();
    }

    @Builder
    public Report(User user, StudyPost studyPost, LocalDateTime date) {
        this.user = user;
        this.studyPost = studyPost;
        this.date = date;
    }

}
