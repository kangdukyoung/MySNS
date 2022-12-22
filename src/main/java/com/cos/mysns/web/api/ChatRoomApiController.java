package com.cos.mysns.web.api;

import com.cos.mysns.Service.ChatRoomService;
import com.cos.mysns.domain.chatRoom.ChatRoom;
import com.cos.mysns.web.dto.chat.ChatRoomDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.mysns.config.auth.PrincipalDetail;
import com.cos.mysns.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class ChatRoomApiController {

    private final ChatRoomService chatRoomService;

    @PostMapping("api/chatroom")
    public ResponseEntity<?> saveRoom(@RequestBody ChatRoomDto chatRoomDto, @AuthenticationPrincipal PrincipalDetail principalDetail){

        ChatRoom chatRoom = chatRoomService.createRoom(chatRoomDto.getRoom_name(),chatRoomDto.getRoom_restrictNumber(),chatRoomDto.getRoom_description(),principalDetail.getUser());
        return new ResponseEntity<>(new CMRespDto<>(1,"채팅방만들기 성공",chatRoom),HttpStatus.CREATED);
    }

    @PostMapping("api/chatroom/{roomId}/up")
    public ResponseEntity<?> EnterRoom(@PathVariable int roomId){
        ChatRoom chatRoom = chatRoomService.increaseExist(roomId);
        return new ResponseEntity<>(new CMRespDto<>(1,"현재인원 +1 성공",chatRoom),HttpStatus.CREATED);
    }
}


