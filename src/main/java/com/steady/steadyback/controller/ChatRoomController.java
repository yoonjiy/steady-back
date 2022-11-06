package com.steady.steadyback.controller;

import com.steady.steadyback.domain.ChatRoom;
import com.steady.steadyback.domain.User;
import com.steady.steadyback.dto.ChatRoomDto;
import com.steady.steadyback.dto.OnetoOneChatRoomDto;
import com.steady.steadyback.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;

    //로그인 유저에 대한 채팅방 목록 반환
    @GetMapping("/room")
    public List<ChatRoomDto> getRooms(@AuthenticationPrincipal User user){
        return chatService.findAllRoomByUser(user);
    }

    //특정 채팅방 정보 반환
    @GetMapping("room/{roomId}")
    public ChatRoomDto getRoom(@PathVariable String roomId){
        return chatService.findRoomByRoomId(roomId);
    }

    //1:1 채팅방 생성, roomName은 target 유저 이름과 일치
    @PostMapping("/room")
    public ChatRoomDto createRoom(@AuthenticationPrincipal User user, @RequestBody OnetoOneChatRoomDto onetoOneChatRoomDto){
        return chatService.createOneToOneChatRoom(user, onetoOneChatRoomDto.getTarget(), onetoOneChatRoomDto.getRoomName());
    }

    //채팅방 삭제
    @DeleteMapping("/room/{roomId}")
    public ChatRoomDto deleteRoom(@PathVariable String roomId){
        return chatService.deleteChatRoom(roomId);
    }

}
