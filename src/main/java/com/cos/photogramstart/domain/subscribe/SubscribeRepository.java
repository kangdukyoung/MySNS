package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{

	@Modifying // DB수정(select, JPA이 제공하는 메서드쿼리에는 적용X)
	@Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())",nativeQuery = true)
	void mySubscribe(int fromUserId, int toUserId);
	
	@Modifying
	@Query(value = "DELETE FROM subscribe WHERE fromUserId=:fromUserId AND toUserId=:toUserId",nativeQuery = true)
	void myUnSubscribe(int fromUserId, int toUserId);
	
	//구독여부확인
	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId",nativeQuery = true)
	int mySubscribeState(int fromUserId, int toUserId);
	
	//구독자수
	@Query(value="SELECT COUNT(*) FROM subscribe WHERE fromUserId = :fromUserId",nativeQuery=true)
	int mySubscribeCount(int fromUserId);
	
}
