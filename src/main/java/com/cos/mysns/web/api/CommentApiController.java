package com.cos.mysns.web.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.mysns.Service.CommentService;
import com.cos.mysns.config.auth.PrincipalDetail;
import com.cos.mysns.domain.comment.Comment;
import com.cos.mysns.web.dto.CMRespDto;
import com.cos.mysns.web.dto.comment.CommentDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class CommentApiController {

	private final CommentService commentService;
	
	@PostMapping("api/comment")
	public ResponseEntity<?> commentSave(@Valid @RequestBody CommentDto commentDto, BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetail principalDetail){

		Comment comment = commentService.createComment(commentDto.getContent(),commentDto.getImageId(), principalDetail.getUser().getId());
		return new ResponseEntity<>(new CMRespDto<>(1,"댓글쓰기 성공",comment),HttpStatus.CREATED);
	}
	

	@DeleteMapping("/api/comment/{id}")
	public ResponseEntity<?> commentDelete(@PathVariable int id){
		
		commentService.deleteComment(id);
		return new ResponseEntity<>(new CMRespDto<>(1,"댓글삭제 성공",null),HttpStatus.OK);
	}
	
}
