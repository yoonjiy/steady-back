package com.steady.steadyback.service;

import com.steady.steadyback.dto.ChatRoomDto;
import com.steady.steadyback.dto.ChatRoomMap;

import java.util.*;

public class ChatService {
    // 전체 채팅방 조회
    public List<ChatRoomDto> findAllRoom(){
        // 채팅방 생성 순서 최신순 반환
        List<ChatRoomDto> chatRooms = new ArrayList<>(ChatRoomMap.getInstance().getChatRooms().values());
        Collections.reverse(chatRooms);

        return chatRooms;
    }

    // roomId 기준으로 채팅방 찾기
    public ChatRoomDto findRoomById(String roomId){
        return ChatRoomMap.getInstance().getChatRooms().get(roomId);
    }

    // 채팅방 만들기
    public ChatRoomDto createChatRoom(String roomName, String chatType){
        ChatRoomDto room = ChatRoomDto.builder()
                .roomId(UUID.randomUUID().toString())
                .roomName(roomName)
                .build();

        room.setUserList(new HashMap<String, String>());

        // map에 채팅룸 저장
        ChatRoomMap.getInstance().getChatRooms().put(room.getRoomId(), room);

        return room;
    }

    // 채팅방 삭제
    public void deleteChatRoom(String roomId){
        ChatRoomMap.getInstance().getChatRooms().remove(roomId);
    }

    // 채팅방 유저 리스트에 유저 추가
    public String addUser(String roomId, String userName){
        ChatRoomDto room = findRoomById(roomId);
        String userUUID = UUID.randomUUID().toString();

        HashMap<String, String> userList = room.getUserList();
        userList.put(userUUID, userName);

        return userUUID;
    }

    // 채팅방 유저 리스트 조회
    public ArrayList<String> getUserList(String roomId){
        ArrayList<String> list = new ArrayList<>();

        ChatRoomDto room = findRoomById(roomId);

        room.getUserList().forEach(
                (key, value) -> list.add(value)
        );

        return list;
    }

}
