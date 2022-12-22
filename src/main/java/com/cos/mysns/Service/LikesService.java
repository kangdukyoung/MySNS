package com.cos.mysns.Service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cos.mysns.domain.likes.LikesRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class LikesService {
	
	private final LikesRepository likesRepository;
	
	@Transactional
	public void 좋아요(int imageId, int principalId) {
		likesRepository.mylikes(imageId, principalId);
		
		
	}
	
	@Transactional
	public void 좋아요취소(int imageId, int principalId) {
		likesRepository.myunlikes(imageId, principalId);
		
		
	}
	
	
	
}
