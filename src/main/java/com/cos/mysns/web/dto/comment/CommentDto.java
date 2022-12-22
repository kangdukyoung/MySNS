package com.cos.mysns.web.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentDto {
	
	@NotBlank //빈값이거나 null, 빈 공백 체크
	private String content;
	
	@NotNull //빈값체크
	private Integer imageId;
	
	//toEntity 가 필요없다.
}
