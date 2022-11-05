package com.steady.steadyback.service;

import com.steady.steadyback.domain.*;
import com.steady.steadyback.dto.ChatRoomDto;
import com.steady.steadyback.dto.ChatRoomMap;
import com.steady.steadyback.util.errorutil.CustomException;
import com.steady.steadyback.util.errorutil.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    // 현재 user가 참여 중인 채팅방 리스트 반환 (roomId, roomName)
    public List<ChatRoomDto> findAllRoomByUser(User user){
        return chatRoomMemberRepository.findAllByUser(user)
                .stream()
                .map(chatRoomMember -> new ChatRoomDto(chatRoomMember.getRoom().getRoomId(), chatRoomMember.getRoom().getRoomName()))
                .collect(Collectors.toList());
    }

    // 1:1 채팅방 개설
    public ChatRoomDto createOneToOneChatRoom(User user, String targetUser, String roomName){
        ChatRoom room = ChatRoom.builder()
                .roomId(UUID.randomUUID().toString())
                .roomName(roomName)
                .build();

        User target = userRepository.findByNickname(targetUser)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        //addMember 메서드로 중복 제거하기
        ChatRoomMember member = ChatRoomMember.builder()
                .room(room)
                .member(user)
                .build();

        ChatRoomMember targetMember = ChatRoomMember.builder()
                .room(room)
                .member(target)
                .build();

        //db에 저장
        chatRoomRepository.save(room);
        chatRoomMemberRepository.save(member);
        chatRoomMemberRepository.save(targetMember);

        return new ChatRoomDto(room.getRoomId(), room.getRoomName());
    }

    // 채팅방 삭제
    public void deleteChatRoom(String roomId){
        ChatRoom room = chatRoomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new CustomException(ErrorCode.CHAT_ROOM_NOT_FOUND));
        chatRoomRepository.delete(room);
    }

    // 채팅방 유저 리스트 조회
    // 채팅방에 유저 추가
    // 채팅방에 유저 삭제



}
