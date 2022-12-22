package com.cos.mysns.web.dto.user;

import com.cos.mysns.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
	private User user;
	private boolean pageOwnerState;
	private int imageCount;
	private int subscribeCount;
	private boolean subscribeState;
}
