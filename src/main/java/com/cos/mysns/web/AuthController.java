package com.cos.mysns.web;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.mysns.Service.AuthService;
import com.cos.mysns.domain.user.User;
import com.cos.mysns.handler.ex.CustomValidationException;
import com.cos.mysns.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final을 DI 할때 사용. 어떻게? final이 걸려있는 모든 객체의 생성자를 만듬.
@Controller
public class AuthController {
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	private final AuthService authService;
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}


	@PostMapping("/auth/signup")	//@Valid -> 별도의 Validation 라이브러리 필요.
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()){

			Map<String,String> errorMap = new HashMap<>();
			for(FieldError error : bindingResult.getFieldErrors()){
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomValidationException("유효성 검사 실패함",errorMap);

		}else{
			User user = signupDto.toEntity();
			User userEntity = authService.signup(user);
			log.info(userEntity.toString());
			return "auth/signin";
		}

		}

}
