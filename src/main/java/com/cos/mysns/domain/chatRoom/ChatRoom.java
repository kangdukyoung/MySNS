package com.cos.mysns.domain.chatRoom;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.cos.mysns.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name; //방 이름

    @JoinColumn(name="creator")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user; //방장

    private String description; //방 설명
    private int restrictNumber; //제한인원
    private int existNumber; //현재인원

    private LocalDateTime createDate;

    @PrePersist //db에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
