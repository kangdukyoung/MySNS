package com.cos.mysns.web.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.mysns.Service.ImageService;
import com.cos.mysns.Service.LikesService;
import com.cos.mysns.config.auth.PrincipalDetail;
import com.cos.mysns.domain.image.Image;
import com.cos.mysns.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class ImageApiController {

	private final ImageService imageService;
	private final LikesService likesService;
	
	@GetMapping("/api/image")
	public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrincipalDetail principalDetail,
			@PageableDefault(size=3) Pageable pageable){
		
		Page<Image> imageList = imageService.loadAllImages(principalDetail.getUser().getId(), pageable);
		return new ResponseEntity<>(new CMRespDto<>(1,"성공",imageList),HttpStatus.OK);
	}

	@PostMapping("/api/image/{imageId}/likes")
	public ResponseEntity<?> likes(@PathVariable int imageId, @AuthenticationPrincipal PrincipalDetail principalDetail){
		likesService.doLike(imageId,principalDetail.getUser().getId());
		return new ResponseEntity<>(new CMRespDto<>(1,"좋아요 성공",null),HttpStatus.CREATED);

	}
	
	@DeleteMapping("/api/image/{imageId}/likes")
	public ResponseEntity<?> unlikes(@PathVariable int imageId, @AuthenticationPrincipal PrincipalDetail principalDetail){
		likesService.notLike(imageId,principalDetail.getUser().getId());
		return new ResponseEntity<>(new CMRespDto<>(1,"좋아요 취소 성공",null),HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
