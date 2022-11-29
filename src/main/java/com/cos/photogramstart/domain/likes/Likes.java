package com.cos.photogramstart.domain.likes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
					name="likes_uk",
					columnNames= {"userId","imageId"} // 유저가 같은 이미지를 두번 좋아요할 수 없으니 중복을 막아준다.
				)
		}
		)
@Entity
public class Likes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name="userId")
	@ManyToOne
	private User user;
	

	@JoinColumn(name="imageId")
	@ManyToOne
	private Image image;
	
	
	
	private LocalDateTime createDate;
	
	@PrePersist //db에 insert되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
	
	
	
	
	
	
}
