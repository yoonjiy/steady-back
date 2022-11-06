package com.steady.steadyback.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OnetoOneChatRoomDto {
    private String roomName;
    private String target;

    public OnetoOneChatRoomDto(String roomName, String target){
        this.roomName = roomName;
        this.target = target;
    }
}
