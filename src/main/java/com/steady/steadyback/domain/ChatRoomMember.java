package com.steady.steadyback.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="chat_room_member")
@Getter
@NoArgsConstructor
public class ChatRoomMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User member;

    @Builder
    public ChatRoomMember(ChatRoom room, User member){
        this.room = room;
        this.member = member;
    }

}
