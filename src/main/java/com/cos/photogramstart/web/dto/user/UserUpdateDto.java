package com.cos.photogramstart.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {

	@NotBlank
	private String name;
	@NotBlank
	private String password;
	private String bio;
	private String phone;
	
	//조금 위험함 수정필요
	public User toEntity() {
		return User.builder()
				.name(name)
				.password(password)
				.bio(bio)
				.phone(phone)
				.build();
	}
}
