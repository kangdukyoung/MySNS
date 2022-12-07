package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {

    @RequestMapping("/mychat/{userId}")
    public String chat(@PathVariable int userId, Model model){
        model.addAttribute("id",userId);
        return "chat/chatting";
    }

    @GetMapping("/chatroom")
    public String chatroom(){
        return "chat/chatroom";
    }

}
