package com.golearn.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.golearn.service.MailService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin(origins = "*")
@RestController
public class MailController {
	
	@Autowired
	private MailService mailService;

	@ApiOperation(value = "입련된 아이디로 이메일 전송")
	@PostMapping(value ="/email-authentication")
	public ResponseEntity<String> sendEmail(@ApiIgnore @RequestHeader("X-USERNO") String userNo, @RequestBody Map<String, Object> map){
		String email = (String)map.get("email");
		int res = mailService.sendMail(email, Integer.parseInt(userNo));
		if(res == 0) {
			return ResponseEntity.ok("전송 실패");
		}
		return ResponseEntity.ok("전송 성공");
	}
	
	@ApiOperation(value = "이메일 인증 확인하기")
	@GetMapping(value = "/email-authentication/{message}")
	public ResponseEntity<Boolean> checkMail(@ApiIgnore @RequestHeader("X-USERNO") String userNo, @PathVariable("message") String message){
		int res = mailService.checkMessage(Integer.parseInt(userNo), message);
		if(res == 0) {
			return ResponseEntity.ok(false);
		}
		mailService.updateCheckMail(Integer.parseInt(userNo));
		return ResponseEntity.ok(true);
	}
}
