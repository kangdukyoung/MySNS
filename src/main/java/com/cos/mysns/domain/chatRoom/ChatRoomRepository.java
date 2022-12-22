package com.cos.mysns.domain.chatRoom;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {

    @Query(value = "SELECT * FROM ChatRoom c ORDER BY c.id DESC",nativeQuery = true)
    List<ChatRoom> findAllDesc();

}
