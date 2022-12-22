package com.cos.mysns.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.mysns.Service.UserService;
import com.cos.mysns.config.auth.PrincipalDetail;
import com.cos.mysns.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;

	//마이페이지
	@GetMapping("/user/{pageUserId}")
	public String user(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetail principalDetail,  Model model) {
		UserProfileDto dto = userService.회원프로필(pageUserId, principalDetail.getUser().getId());
		model.addAttribute("dto", dto);
		return "user/profile";
	}

	//회원정보수정
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		return "user/update";
	}

	//채팅
//	@GetMapping("/user/{id}/chat")
//	public String chat(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principalDetail) {
//		return "user/chat";
//	}

}
