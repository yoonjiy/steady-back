package com.steady.steadyback.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_message")
@NoArgsConstructor
@Getter
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @NotNull
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Column
    @NotNull
    private String sender;

    @Column
    @NotNull
    private String message;

    @CreationTimestamp
    @NotNull
    @Column(updatable = false)
    private LocalDateTime sendTime;

}
