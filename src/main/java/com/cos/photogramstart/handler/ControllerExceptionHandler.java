package com.cos.photogramstart.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

import java.util.Map;

@RestController
@ControllerAdvice //모든 exception을 낚아챈다.
public class ControllerExceptionHandler {

	//1. 클라이언트에게 응답할때는 Script 좋음.
	//2. Ajax통신할때는 CMRespDto 좋음
	
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		if(e.getErrorMap() == null) {
			return Script.back(e.getMessage());
		}else {
			return Script.back(e.getErrorMap().toString());
		}

		
	}//여기는 자바스크립트 리턴
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<CMRespDto<?>> validationApiException(CustomValidationApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);
		
	}//여기는 데이터리턴(Ajax 통신)
	
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<CMRespDto<?>> ApiException(CustomApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null),HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(CustomException.class)
	public String Exception(CustomException e) {
			return Script.back(e.getMessage());
	}
}
