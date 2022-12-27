package com.cos.mysns.web;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.mysns.Service.ImageService;
import com.cos.mysns.config.auth.PrincipalDetail;
import com.cos.mysns.domain.image.Image;
import com.cos.mysns.handler.ex.CustomValidationException;
import com.cos.mysns.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {

	private final ImageService imageService;


	@GetMapping({"/","/image/story"})
	public String story(@AuthenticationPrincipal PrincipalDetail principalDetail) {
		return "image/story";
	}

	@GetMapping("/image/upload")
	public String upload() {
		return "image/upload";
	}
	
	@PostMapping("/image")
	public String uploadImage(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		
		if(imageUploadDto.getFile().isEmpty()){
			throw new CustomValidationException("이미지를 넣어주세요.",null);
		}
		else {
			imageService.uploadImage(imageUploadDto, principalDetail);
			return "redirect:/user/"+principalDetail.getUser().getId();
		}
	}


	@GetMapping("/delete/{imageId}")
	public String deleteImage(@PathVariable int imageId, @AuthenticationPrincipal PrincipalDetail principalDetail){
		imageService.deleteImage(imageId);

		return "redirect:/user/"+principalDetail.getUser().getId();
	}
}
