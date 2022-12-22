package com.cos.mysns.web.dto.chat;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class ChatRoomDto {
    @NotEmpty
    private String room_name;

    @Max(100)
    @Min(1)
    private int room_restrictNumber;
    private String room_description;

}
