package com.cos.mysns.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.mysns.Service.SubscribeService;
import com.cos.mysns.config.auth.PrincipalDetail;
import com.cos.mysns.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

	private final SubscribeService subscribeService;
	
	@PostMapping("/api/subscribe/{toUserId}")
	public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetail principalDetail, @PathVariable int toUserId){
		subscribeService.doSubscribe(principalDetail.getUser().getId(),toUserId);
		return new ResponseEntity<>(new CMRespDto<>(1,"구독하기 성공",null),HttpStatus.OK);
	}
	
	@DeleteMapping("/api/subscribe/{toUserId}")
	public ResponseEntity<?> unsubscribe(@AuthenticationPrincipal PrincipalDetail principalDetail, @PathVariable int toUserId){
		subscribeService.notSubscribe(principalDetail.getUser().getId(), toUserId);
		return new ResponseEntity<>(new CMRespDto<>(1,"구독취소 성공",null),HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
}
