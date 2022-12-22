package com.cos.mysns.domain.image;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Integer>{

	@Query(value = "SELECT * FROM image WHERE userId = :principalId OR userId IN(SELECT toUserId FROM subscribe WHERE fromUserId=:principalId) ORDER BY id DESC",nativeQuery = true)
	Page<Image> mystory(int principalId, Pageable pageable);
	
	//인기사진
	@Query(value = "SELECT i.* FROM image i INNER JOIN (SELECT imageId, COUNT(imageId) likeCount FROM likes GROUP BY imageId) c ON i.id = c.imageId ORDER BY likeCount DESC",nativeQuery = true)
	List<Image> myPopular();
	
}
