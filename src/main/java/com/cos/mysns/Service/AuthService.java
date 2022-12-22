package com.cos.mysns.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.mysns.domain.user.User;
import com.cos.mysns.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // IoC, 트랜잭션 관리 해줌
public class AuthService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional // Write(insert, update, delete할때)
	public User signup(User user) {

		//비밀번호 encode
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);

		user.setRole("ROLE_USER");
		
		User userEntity = userRepository.save(user);

		return userEntity;
	}
	
	
	
	
	
	
	
	
}
