package com.steady.steadyback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
    //입장, 퇴장 이벤트, 채팅 이벤트 분리
    public enum MessageType{
        ENTER, TALK, LEAVE;
    }
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private String sendTime; //발송 시간
}
