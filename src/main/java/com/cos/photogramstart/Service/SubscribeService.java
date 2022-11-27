package com.cos.photogramstart.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;
	private final EntityManager em;
	
	
	
	@Transactional
	public List<SubscribeDto> 구독리스트(int principalId, int pageUserId){
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT user.id, user.username, user.profileImageUrl, ");
		sb.append("if ((SELECT 1 FROM subscribe WHERE fromUserId=? AND toUserId=user.id),1,0) subscribeState, ");
		sb.append("if ((?=user.id),1,0) eqaulUserState ");
		sb.append("FROM user inner JOIN subscribe ");
		sb.append("ON user.id=subscribe.toUserId ");
		sb.append("WHERE subscribe.fromUserId=?");
		
		//1물음표 principalId
		//2물음표 principalId
		//3물음표 pageUserId
		
		//쿼리완성
		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1,principalId)
				.setParameter(2, principalId)
				.setParameter(3, pageUserId);
		
		//쿼리실행(qlrm 라이브러리필요 = Dto에 db결과를 매핑하기 위해서)
		JpaResultMapper result = new JpaResultMapper();
		List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);
		
		return subscribeDtos;
	}
	
	
	@Transactional
	public void 구독하기(int fromUserId , int toUserId) {
		try {
			subscribeRepository.mySubscribe(fromUserId, toUserId);
		}catch(Exception e) {
			throw new CustomApiException("이미 구독을 했습니다.");
		}

	}
	
	
	@Transactional
	public void 구독취소(int fromUserId , int toUserId) {
		subscribeRepository.myUnSubscribe(fromUserId, toUserId);
	}

	
	
}
