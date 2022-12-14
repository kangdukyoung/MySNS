package com.cos.photogramstart.domain.chatRoom;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private String password; //방 비밀번호

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
