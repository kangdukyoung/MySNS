package com.cos.mysns.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.mysns.domain.image.Image;
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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //번호증가전략이 db를 따라간다.
	private int id;

	@Column(nullable=false)
	private String name;

	@Column(length = 100, unique = true)
	private String username;

	@Column(nullable=false)
	private String password;

	
	@Column(nullable=false)	
	private String email;
	

	private String phone;

	private String bio;
	private String profileImageUrl;
	private String role;

	//나는 연관관계의 주인이  아니다. 그니까 칼럼만들지 마.
	//Lazy = user를 select할때 image들을 가져오지마. 대신 getimages()함수의 image들이 호출될떄 가져와
	//Eager = User를 select할때 조인해서 전부가져와.
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"user"}) //무한참조방지
	private List<Image> images; //양방향매핑
	
	private LocalDateTime createDate;
	
	@PrePersist //db에 insert되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	

}
