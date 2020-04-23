package com.biz.hello.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.hello.domain.UserVO;

@Service
public class UserService {

	public List<UserVO> getUserList() {
		
		List<UserVO> userList = new ArrayList<>();
		
		userList.add( UserVO.builder()
				.username("홍길동")
				.password("1234")
				.email("freeswoo@naver.com")
				.phone("010-111-1234")
				.address("서울")
				.build()
			);
		userList.add( UserVO.builder()
				.username("성춘향")
				.password("1234")
				.email("sung@naver.com")
				.phone("010-222-2222")
				.address("남원")
				.build()
			);
		userList.add( UserVO.builder()
				.username("이몽룡")
				.password("1234")
				.email("lee@naver.com")
				.phone("010-333-3333")
				.address("익산")
				.build()
			);
		
		return userList;	
	}
	
}
