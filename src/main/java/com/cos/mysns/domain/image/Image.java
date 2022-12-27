package com.cos.mysns.domain.image;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.cos.mysns.domain.comment.Comment;
import com.cos.mysns.domain.likes.Likes;
import com.cos.mysns.domain.user.User;
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
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String caption;
	private String postImageUrl; //사진을 전송받아서 그 사진을 서버에 특정폴더에 저장 db에 그 저장된 경로를 insert
	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name="userId")
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	//좋아요
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "image", cascade = {CascadeType.ALL},orphanRemoval = true)
	private List<Likes> likes;

	//댓글
	@OrderBy("id DESC")
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "image") //외래키에대한 자바변수 이름을 적어준다.
	private List<Comment> comments;

	
	@Transient //DB에 컬럼이 만들어지지 않는다.
	private boolean likeState;
	
	@Transient
	private int likeCount;

	private LocalDateTime createDate;
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
