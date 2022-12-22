package com.cos.mysns.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;

import com.cos.mysns.domain.subscribe.SubscribeRepository;
import com.cos.mysns.handler.ex.CustomApiException;
import com.cos.mysns.web.dto.subscribe.SubscribeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;
	private final EntityManager em;

	@Transactional
	public List<SubscribeDto> subList(int principalId, int pageUserId){
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT user.id, user.name, user.username, user.profileImageUrl, ");
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
	public void doSubscribe(int fromUserId , int toUserId) {
		try {
			subscribeRepository.mySubscribe(fromUserId, toUserId);
		}catch(Exception e) {
			throw new CustomApiException("이미 구독을 했습니다.");
		}

	}
	
	
	@Transactional
	public void notSubscribe(int fromUserId , int toUserId) {
		subscribeRepository.myUnSubscribe(fromUserId, toUserId);
	}

	
	
}
