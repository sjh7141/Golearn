package com.golearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.golearn.domain.UserDto;
import com.golearn.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "유저 회원 가입")
	@PostMapping(value = "/users")
	public ResponseEntity<String> singup(@RequestBody UserDto dto) {
		int res = userService.save(dto);
		if(res == 0) {
			ResponseEntity.ok("등록 실패");
		}
		return ResponseEntity.ok("등록 완료");
	}
	
	@ApiOperation(value = "유저 탈퇴하기 (사실 숨기기임)")
	@DeleteMapping(value = "/users")
	public ResponseEntity<String> removeUser(){
		
		return null;
	}
}
