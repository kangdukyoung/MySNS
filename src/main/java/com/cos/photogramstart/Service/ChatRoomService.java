package com.cos.photogramstart.Service;

import com.cos.photogramstart.domain.chatRoom.ChatRoom;
import com.cos.photogramstart.domain.chatRoom.ChatRoomRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public ChatRoom createRoom(String name, int restrictNumber, String description, String password, User user){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        chatRoom.setRestrictNumber(restrictNumber);
        chatRoom.setDescription(description);

        //변경필요
        chatRoom.setPassword(password);


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
