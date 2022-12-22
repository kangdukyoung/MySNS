package com.cos.mysns.web;

import com.cos.mysns.Service.ChatRoomService;
import com.cos.mysns.config.auth.PrincipalDetail;
import com.cos.mysns.domain.chatRoom.ChatRoom;
import com.cos.mysns.handler.ex.CustomValidationException;
import com.cos.mysns.web.dto.chat.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatRoomService chatRoomService;

    @RequestMapping("/mychat/{userId}")
    public String chat(@PathVariable int userId, Model model){
        model.addAttribute("id",userId);
        return "chat/chatting";
    }

    @GetMapping("/chatroom")
    public String chatroom(Model model){
        //chatroom db에서 불러오기
        List<ChatRoom> chatrooms = chatRoomService.loadAllChatrooms();
        model.addAttribute("chatrooms",chatrooms);
        return "chat/chatroom";
    }


    //채팅방 진입
    @GetMapping("/createRoom")
    public String getCreateRoom(){
        return "chat/createRoom";
    }

    //채팅방 생성
    @PostMapping("/createRoom")
    public String postCreateRoom(@Valid ChatRoomDto chatRoomDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetail principalDetail) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패", errorMap);
        } else {
            chatRoomService.createRoom(chatRoomDto.getRoom_name(), chatRoomDto.getRoom_restrictNumber(), chatRoomDto.getRoom_description(), principalDetail.getUser());
            return "redirect:/chatroom";
        }
    }

    @GetMapping("/chatroom/{room_id}/mychat/{user_id}")
    public String enterRoom(@PathVariable int room_id, @PathVariable int user_id, Model model){
        ChatRoom chatroom = chatRoomService.loadOneChatroom(room_id);
        model.addAttribute("chatroom",chatroom);
        return "chat/chatting";

    }

    //채팅방 삭제
    @GetMapping("/chatroom/delete/{room_id}")
    public String deleteRoom(@PathVariable int room_id,@AuthenticationPrincipal PrincipalDetail principalDetail){
        chatRoomService.deleteOneRoom(room_id);
        return "redirect:/chatroom";
    }




}
