package com.cos.mysns.Service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cos.mysns.domain.comment.Comment;
import com.cos.mysns.domain.comment.CommentRepository;
import com.cos.mysns.domain.image.Image;
import com.cos.mysns.domain.image.ImageRepository;
import com.cos.mysns.domain.user.User;
import com.cos.mysns.domain.user.UserRepository;
import com.cos.mysns.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	private final ImageRepository imageRepository;
	
	@Transactional
	public Comment createComment(String content, int imageId, int principalId) {
		

		Image imageEntity = imageRepository.findById(imageId).orElseThrow(()->{
			throw new CustomApiException("이미지를 불러올 수 없습니다.");
		});
		
		User userEntity = userRepository.findById(principalId).orElseThrow(()->{
			throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
		});
		
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setImage(imageEntity);
		comment.setUser(userEntity);
		
		
		return commentRepository.save(comment);
	}
	
	@Transactional
	public void deleteComment(int id) {
		try {
			commentRepository.deleteById(id);
		}catch(Exception e) {
			throw new CustomApiException(e.getMessage());
		}

	}







}
