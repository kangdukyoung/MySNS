package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.photogramstart.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity // 해당파일로 시큐리티를 활성화, 자동으로 csrf 보호기능 활성화
@Configuration //IoC
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final OAuth2DetailsService oAuth2DetailsService;

	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/user/**","/chatroom/**","/image/**","/subscribe/**","/comment/**","/api/**").authenticated()
			.anyRequest().permitAll()
			.and()
		.formLogin()
			.loginPage("/auth/signin")
			.loginProcessingUrl("/auth/signin")
			.defaultSuccessUrl("/")
			.and()
			.oauth2Login() //oauth2로그인도 추가로 진행
			.userInfoEndpoint() //oauth2로그인 성공 후에 사용자 정보를 바로 가져온다.
			.userService(oAuth2DetailsService);
	}
}
