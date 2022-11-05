package com.steady.steadyback.dto;

import com.steady.steadyback.domain.ChatRoom;
import com.steady.steadyback.domain.ChatRoomMember;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Builder
public class ChatRoomDto {
    private String roomId;
    private String roomName;

    //참여 멤버리스트 저장하는 필드 추가하기
    //private ArrayList<String> members;

    public ChatRoomDto(String roomId, String roomName){
        this.roomId = roomId;
        this.roomName = roomName;
    }

    // 일단 보류
    //    private int userCount; // 채팅방 인원수
    //    private int maxUserCnt; // 채팅방 최대 인원 제한
    //    private String roomPwd; // 채팅방 pwd
    //    private boolean isLocked; // 채팅방 잠금 여부

    //private HashMap<String, String> memberList;
}
