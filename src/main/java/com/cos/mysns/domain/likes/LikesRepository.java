package com.cos.mysns.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Integer>{

	@Modifying
	@Query(value="INSERT INTO likes(imageId, userId, createDate) VALUES(:imageId, :userId, NOW())",nativeQuery = true)
	int mylikes(int imageId, int userId);
	
	@Modifying
	@Query(value="DELETE FROM likes WHERE imageId = :imageId AND userId = :userId",nativeQuery = true)
	int myunlikes(int imageId, int userId);
	
	
	
}
