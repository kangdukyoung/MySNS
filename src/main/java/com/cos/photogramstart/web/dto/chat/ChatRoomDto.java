package com.cos.photogramstart.web.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
public class ChatRoomDto {

    private String room_name;
    private String room_creator;



}
