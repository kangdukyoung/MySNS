package com.cos.photogramstart.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetail;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	

	@Transactional
	public List<Image> 인기이미지() {
		
		return imageRepository.myPopular();
	}
	
	
	
	@Transactional
	public Page<Image> 이미지불러오기(int principalId, Pageable pageable) {
		Page<Image> imageList = imageRepository.mystory(principalId,pageable);
		
		
		
		//2로 로그인
		//images에 좋아요상태 담아야됨.
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
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetail principalDetail) {
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
	
	
	
	
	
}
