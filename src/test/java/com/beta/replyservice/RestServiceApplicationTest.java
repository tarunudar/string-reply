package com.beta.replyservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestServiceApplicationTest {

	@Test
	public void contextLoads() {
		ReplyMessage replyMessage = new ReplyMessage("11-testvalue");
		assertThat(replyMessage.getMessage().contains("-"));
	}

}
