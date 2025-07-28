package com.marondal.marondalgram.user.sample;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.marondal.marondalgram.user.service.UserService;

import net.datafaker.Faker;

@SpringBootTest
class UserSampleAddTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void sampleAdd() {
		
		Faker facker = new Faker();
		Faker fackerKo = new Faker(new Locale("ko"));
		
		for(int i = 0; i < 10000; i++) {
			userService.createUser(
					facker.internet().username()
					, facker.internet().password()
					, fackerKo.name().fullName()
					, facker.internet().emailAddress());
		}
		
	}
	

}
