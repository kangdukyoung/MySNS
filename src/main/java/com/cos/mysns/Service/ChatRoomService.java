package com.cos.mysns.Service;

import com.cos.mysns.domain.chatRoom.ChatRoom;
import com.cos.mysns.domain.chatRoom.ChatRoomRepository;
import com.cos.mysns.domain.user.User;
import com.cos.mysns.handler.ex.CustomApiException;
import com.cos.mysns.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public ChatRoom createRoom(String name, int restrictNumber, String description, User user){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        chatRoom.setRestrictNumber(restrictNumber);
        chatRoom.setDescription(description);

        chatRoom.setUser(user);
        return chatRoomRepository.save(chatRoom);
    }

    @Transactional
    public List<ChatRoom> loadAllChatrooms(){
        return chatRoomRepository.findAllDesc();
    }

    @Transactional
    public ChatRoom loadOneChatroom(int room_id){
        ChatRoom chatroom = chatRoomRepository.findById(room_id).orElseThrow(()->{
            return new CustomValidationApiException("찾을 수 없는 방입니다.");
        });
        return chatroom;
    }
    @Transactional
    public ChatRoom increaseExist(int room_id){
        ChatRoom chatroom = chatRoomRepository.findById(room_id).orElseThrow(()->{
            return new CustomValidationApiException("찾을 수 없는 방입니다.");
        });
        chatroom.setExistNumber(chatroom.getExistNumber()+1);
        return chatRoomRepository.save(chatroom);
    }
    @Transactional
    public ChatRoom decreaseExist(int room_id){
        ChatRoom chatroom = chatRoomRepository.findById(room_id).orElseThrow(()->{
            return new CustomValidationApiException("찾을 수 없는 방입니다.");
        });

        if(chatroom.getExistNumber()!=0)
            chatroom.setExistNumber(chatroom.getExistNumber()-1);
        return chatRoomRepository.save(chatroom);
    }

    @Transactional
    public void deleteOneRoom(int room_id){

        try {
            chatRoomRepository.deleteById(room_id);
        }catch(Exception e) {
            throw new CustomApiException(e.getMessage());
        }

    }

}
