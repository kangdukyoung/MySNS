package com.cos.photogramstart.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;


//@Component //RestController, Service 모든것들이 component를 상속해서 만들어져있다.
//@Aspect
public class ValidationAdvice {

	@Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

		Object[] args = proceedingJoinPoint.getArgs();
		
		for(Object arg: args) {
		if(arg instanceof BindingResult)
		{
			BindingResult bindingResult = (BindingResult) arg;
			
			if(bindingResult.hasErrors())
			{
				Map<String,String> errorMap = new HashMap<>();
				
				for(FieldError error: bindingResult.getFieldErrors())
				{
					errorMap.put(error.getField(),error.getDefaultMessage());
				}
				throw new CustomValidationApiException("유효성검사 실패함",errorMap);
			}

			}
		}	
		//proceedingJoinPoint => api쪽 함수의 모든곳에 접근할 수 있는 변수
		//api쪽의 함수보다 먼저 실행이된다.
	
		return proceedingJoinPoint.proceed(); //api쪽 함수가 시작됨.
	}
	
	
	
	
	
	
	@Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg: args) {
		if(arg instanceof BindingResult)
		{
			BindingResult bindingResult = (BindingResult) arg;
			
			if(bindingResult.hasErrors())
			{
				Map<String,String> errorMap = new HashMap<>();
				
				for(FieldError error: bindingResult.getFieldErrors())
				{
					errorMap.put(error.getField(),error.getDefaultMessage());
				}
				throw new CustomValidationException("유효성검사 실패함",errorMap);
			}

			}
		}	
		
		
		
		
		
		
		
		
		
		
		return proceedingJoinPoint.proceed();
	}
}
