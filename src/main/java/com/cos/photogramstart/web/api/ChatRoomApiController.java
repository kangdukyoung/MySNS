package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.cos.photogramstart.Service.ChatRoomService;
import com.cos.photogramstart.Service.ChatService;
import com.cos.photogramstart.domain.chatRoom.ChatRoom;
import com.cos.photogramstart.web.dto.chat.ChatRoomDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.Service.CommentService;
import com.cos.photogramstart.config.auth.PrincipalDetail;
import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.comment.CommentDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class ChatRoomApiController {

    private final ChatRoomService chatRoomService;

    @PostMapping("api/chatroom")
    public ResponseEntity<?> saveRoom(@RequestBody ChatRoomDto chatRoomDto, @AuthenticationPrincipal PrincipalDetail principalDetail){

        System.out.println("------------------------");
        System.out.println(chatRoomDto.getRoom_name());
        System.out.println(chatRoomDto.getRoom_creator());
        ChatRoom chatRoom = chatRoomService.createRoom(chatRoomDto.getRoom_name(),chatRoomDto.getRoom_creator());

        return new ResponseEntity<>(new CMRespDto<>(1,"채팅방만들기 성공",chatRoom),HttpStatus.CREATED);
    }

//    @DeleteMapping("/api/comment/{id}")
//    public ResponseEntity<?> commentDelete(@PathVariable int id){
//
//        commentService.댓글삭제(id);
//        return new ResponseEntity<>(new CMRespDto<>(1,"댓글삭제 성공",null),HttpStatus.OK);
//    }

}
