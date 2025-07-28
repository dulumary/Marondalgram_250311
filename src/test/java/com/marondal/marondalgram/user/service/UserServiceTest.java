package com.marondal.marondalgram.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.marondal.marondalgram.user.domain.User;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void joinAndLoginSuccess() {
		
		// given : 테스트에 필요한 정보 준비
		String loginId = "testid";
		String password = "asdf";
		String name = "김인규";
		String email = "lecture@hagulu.com";
	
		// when : 기능 수행
		boolean result = userService.createUser(loginId, password, name, email);
		
		// then : 수행결과 검증
		// 성공적으로 수행되었다면, true가 리턴되어야 해
		// assert 테스트 통과 여부를 판단하는 기준을 설정해주는 역할
		assertTrue(result);
		
		// when
		boolean isDuplicate = userService.isDuplicateId(loginId);
		
		// then 
		// 성공적으로 진행되었다면 중복이 되어서 ture가 리턴되어야 해
		assertTrue(isDuplicate);
		
		// when
		User user = userService.getUser(loginId, password);
		
		// then
		// 정상적으로 수행되었다면, null이 리턴되면 안되고, 조회된 결과의 이름과 이메일이 가입한 값과 일치해야해
		assertNotNull(user);
		assertEquals(name, user.getName());
		assertEquals(email, user.getEmail());
		
	}
	

}
