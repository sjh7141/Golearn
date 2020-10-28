package com.golearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.golearn.domain.UserDto;
import com.golearn.service.UserService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

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
			return ResponseEntity.ok("등록 실패");
		}
		return ResponseEntity.ok("등록 완료");
	}
	
	@ApiOperation(value = "유저 탈퇴하기 (사실 숨기기임)")
	@DeleteMapping(value = "/users")
	public ResponseEntity<String> removeUser(@ApiIgnore @RequestHeader("X-USERNO") String no){
		int res = userService.disableUser(Integer.parseInt(no));
		if(res == 0) {
			return ResponseEntity.ok("탈퇴 실패");
		}
		return ResponseEntity.ok("탈퇴 성공");
	}
	
	@ApiOperation(value = "유저 정보 조회하기")
	@GetMapping(value = "/users")
	public ResponseEntity<UserDto> findUser(@ApiIgnore @RequestHeader("X-USERNO") String no){
		UserDto dto = userService.findByUsername(Integer.parseInt(no));
		return ResponseEntity.ok(dto);
	}
	
	@ApiOperation(value = "ID 중복 체크하기(true : 사용가능, false : 사용불가능)")
	@GetMapping(value = "/id-check/{mbr_id}")
	public ResponseEntity<Boolean> isDuplicationId(@PathVariable("mbr_id") String username){
		int res = userService.checkByUsername(username);
		if(res == 0) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}
	
	@ApiOperation(value = "Email 중복 체크하기(true : 사용가능, false : 사용불가능)")
	@GetMapping(value = "/email-check/{mbr_email}")
	public ResponseEntity<Boolean> isDuplicationEmail(@PathVariable("mbr_email") String email){
		int res = userService.checkByEmail(email);
		if(res == 0) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}
	
}
