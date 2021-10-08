package com.beta.replyservice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyMessage {

	private String message;
	private String status;

	public ReplyMessage(String string) { }

}