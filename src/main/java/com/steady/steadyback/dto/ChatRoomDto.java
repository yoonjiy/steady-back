package com.steady.steadyback.dto;

import lombok.*;

import java.util.HashMap;
import java.util.UUID;

@Data
@Builder
public class ChatRoomDto {
    private String roomId;
    private String roomName;

    // 일단 보류
    //    private int userCount; // 채팅방 인원수
    //    private int maxUserCnt; // 채팅방 최대 인원 제한
    //    private String roomPwd; // 채팅방 pwd
    //    private boolean isLocked; // 채팅방 잠금 여부

    private HashMap<String, String> userList;
}
