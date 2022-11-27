package com.cos.photogramstart.Service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.comment.CommentRepository;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	private final ImageRepository imageRepository;
	
	@Transactional
	public Comment 댓글쓰기(String content, int imageId, int principalId) {
		

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
	public void 댓글삭제(int id) {
		try {
			commentRepository.deleteById(id);
		}catch(Exception e) {
			throw new CustomApiException(e.getMessage());
		}

	}







}
