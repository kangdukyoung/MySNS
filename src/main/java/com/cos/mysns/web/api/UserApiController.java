package com.cos.mysns.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.mysns.Service.SubscribeService;
import com.cos.mysns.Service.UserService;
import com.cos.mysns.config.auth.PrincipalDetail;
import com.cos.mysns.domain.user.User;
import com.cos.mysns.web.dto.CMRespDto;
import com.cos.mysns.web.dto.subscribe.SubscribeDto;
import com.cos.mysns.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService;
	private final SubscribeService subscribeService;
	
	@PutMapping("/api/user/{principalId}/profileImageUrl")
	public ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId, MultipartFile profileImageFile,
			@AuthenticationPrincipal PrincipalDetail principalDetail){
		User userEntity = userService.회원프로필사진변경(principalId,profileImageFile);
		principalDetail.setUser(userEntity);
		return new ResponseEntity<>(new CMRespDto<>(1,"프로필사진변경 성공", null),HttpStatus.OK);
	}
	
	
	@GetMapping("api/user/{pageUserId}/subscribe")
	public ResponseEntity<?> subscribeList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetail principalDetail){
		
		List<SubscribeDto> subscribeDto = subscribeService.subList(principalDetail.getUser().getId(), pageUserId);
		return new ResponseEntity<>(new CMRespDto<>(1,"구독자 리스트 가져오기 성공",subscribeDto),HttpStatus.OK);
	}
	
	
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(
			@PathVariable int id, 
			@Valid UserUpdateDto userUpdateDto,
			BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetail principalDetail) {
		
			User userEntity = userService.modify_user(id, userUpdateDto.toEntity());
			principalDetail.setUser(userEntity); //세션정보변경
			return new CMRespDto<>(1,"회원수정완료",userEntity);
		
		
		
		
		
	}
}
