package com.cos.photogramstart.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.Service.UserService;
import com.cos.photogramstart.config.auth.PrincipalDetail;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	@GetMapping("/user/{pageUserId}")
	public String user(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetail principalDetail,  Model model) {
		UserProfileDto dto = userService.회원프로필(pageUserId, principalDetail.getUser().getId());
		model.addAttribute("dto", dto);
		return "user/profile";
	}


	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		return "user/update";
	}
}
