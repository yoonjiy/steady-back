package com.steady.steadyback.service;

import com.steady.steadyback.domain.*;
import com.steady.steadyback.dto.ChatRoomDto;
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

    // 특정 채팅방 정보 반환 (roomId, roomName, members)
    public ChatRoomDto findRoomByRoomId(String roomId){
        ChatRoom room = chatRoomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new CustomException(ErrorCode.CHAT_ROOM_NOT_FOUND));
        List<String> members = findMemberListByRoomId(roomId);
        return new ChatRoomDto(room.getRoomId(), room.getRoomName(), members);
    }

    // 1:1 채팅방 개설
    public ChatRoomDto createOneToOneChatRoom(User user, String targetUser, String roomName){
        ChatRoom room = ChatRoom.builder()
                .roomId(UUID.randomUUID().toString())
                .roomName(roomName)
                .build();

        chatRoomRepository.save(room);

        addMember(room.getRoomId(), user.getNickname());
        addMember(room.getRoomId(), targetUser);

        return new ChatRoomDto(room.getRoomId(), room.getRoomName());
    }

    // 채팅방 삭제
    public ChatRoomDto deleteChatRoom(String roomId){
        ChatRoom room = chatRoomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new CustomException(ErrorCode.CHAT_ROOM_NOT_FOUND));
        chatRoomRepository.delete(room); //chatRoomMember cascade 삭제되어야함.
        return new ChatRoomDto(room.getRoomId(), room.getRoomName());
    }

    public void addMember(String roomId, String memberName){
        ChatRoom room = chatRoomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new CustomException(ErrorCode.CHAT_ROOM_NOT_FOUND));
        User member = userRepository.findByNickname(memberName)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        ChatRoomMember saveMember = ChatRoomMember.builder()
                .room(room)
                .member(member)
                .build();

        chatRoomMemberRepository.save(saveMember);
    }

    // 채팅방 유저 리스트 조회
    public List<String> findMemberListByRoomId(String roomId){
        List<String> members = new ArrayList<>();

        chatRoomMemberRepository.findAllByRoom(roomId)
                .stream()
                .map(chatRoomMember -> members.add(chatRoomMember.getMember().getNickname()))
                .collect(Collectors.toList());

        return members;
    }

    // 채팅방에 유저 삭제



}
