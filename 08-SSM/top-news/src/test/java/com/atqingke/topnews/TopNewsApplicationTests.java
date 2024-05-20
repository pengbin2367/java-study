package com.atqingke.topnews;

import com.atqingke.topnews.util.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class TopNewsApplicationTests {

	@Autowired
	private JwtHelper jwtHelper;

	@Test
	void contextLoads() {
		String token = jwtHelper.createToken(1L);
		log.warn("token = {}", token);
		Long userId = jwtHelper.getUserId(token);
		log.warn("userId = {}", userId);
		boolean expiration = jwtHelper.isExpiration(token);
		log.warn("expiration = {}", expiration);
	}

}
