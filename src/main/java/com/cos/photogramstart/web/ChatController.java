package com.cos.photogramstart.web;

import com.cos.photogramstart.Service.ChatRoomService;
import com.cos.photogramstart.config.auth.PrincipalDetail;
import com.cos.photogramstart.domain.chatRoom.ChatRoom;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.web.dto.chat.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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


    @GetMapping("/createRoom")
    public String getCreateRoom(){
        return "chat/createRoom";
    }

    @PostMapping("/createRoom")
    public String postCreateRoom(ChatRoomDto chatRoomDto, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail){
        chatRoomService.createRoom(chatRoomDto.getRoom_name(),chatRoomDto.getRoom_restrictNumber(), principalDetail.getUser());
        return "redirect:/chatroom";
    }


    @GetMapping("/chatroom/{room_id}/mychat/{user_id}")
    public String enterRoom(@PathVariable int room_id, @PathVariable int user_id, Model model){
        ChatRoom chatroom = chatRoomService.loadOneChatroom(room_id);
        model.addAttribute("chatroom",chatroom);
        return "chat/chatting";

    }






}
