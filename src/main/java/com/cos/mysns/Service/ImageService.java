package com.cos.mysns.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.cos.mysns.handler.ex.CustomApiException;
import com.cos.mysns.handler.ex.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cos.mysns.config.auth.PrincipalDetail;
import com.cos.mysns.domain.image.Image;
import com.cos.mysns.domain.image.ImageRepository;
import com.cos.mysns.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;

	//모든 이미지 불러오기
	@Transactional
	public Page<Image> loadAllImages(int principalId, Pageable pageable) {
		Page<Image> imageList = imageRepository.mystory(principalId,pageable);

		//2로 로그인
		//images에 좋아요 담기
		imageList.forEach((image)->{
			image.setLikeCount(image.getLikes().size()); //좋아요 수 넣어주기
			
			image.getLikes().forEach((like)->{
				if(like.getUser().getId() == principalId) { //해당 이미지를 좋아요한 사람을 찾아서 현재 로그인한 사람과 같다면
					image.setLikeState(true);
				}
			});
		});
		
		return imageList;
	}
	

	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional
	public void uploadImage(ImageUploadDto imageUploadDto, PrincipalDetail principalDetail) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		// 통신, I/O -> 예외가 발생할 수 있다.
		try {
			Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//image테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName, principalDetail.getUser());
		imageRepository.save(image);
	}

	//이미지 삭제
	@Transactional
	public void deleteImage(int id){
		try {
			imageRepository.deleteById(id);
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

}
