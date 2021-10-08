package com.beta.replyservice;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyController {

	@GetMapping("/reply")
	public ReplyMessage replying() {
		return new ReplyMessage("Message is empty");
	}

	//@GetMapping("/reply/{message}")
	public ReplyMessage replying(@PathVariable String message) {
		return new ReplyMessage(message);
	}

	@GetMapping("/reply/{message}")
	public ResponseEntity<ReplyMessage> replyMessage(@PathVariable String message) {
		ReplyMessage replyMessage = new ReplyMessage(message);
		String[] strA = message.split("-");
		boolean flag = strA[0].contains("1") || strA[0].contains("2");
		if (!flag) {
			replyMessage.setMessage("Invalid Input");
			return new ResponseEntity<>(replyMessage, HttpStatus.BAD_REQUEST);
		}
		String reply = caseVal(strA[0], strA[1]);
		replyMessage.setMessage(reply);
		return new ResponseEntity<>(replyMessage, HttpStatus.OK);
	}

	public String caseVal(String str, String data) {
		String retVal = "";
		StringBuilder stb = new StringBuilder();
		String strHex= DigestUtils.md5Hex(data);
			if ((str.charAt(0) == '1' && (str.charAt(1) == '1'))) {
				retVal = data;
			} else if ((str.charAt(0) == '1' && (str.charAt(1) == '2'))) {
				stb.append(data);
				stb.reverse();
				retVal = DigestUtils.md5Hex(stb.toString());
			} else if ((str.charAt(0) == '2' && (str.charAt(1) == '1'))) {
				stb.append(strHex);
				stb.reverse();
				retVal = stb.toString();
			} else if ((str.charAt(0) == '2' && (str.charAt(1) == '2'))) {
				retVal = DigestUtils.md5Hex(strHex);
			}
		return retVal;
	}
}