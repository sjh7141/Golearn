package com.golearn.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.golearn.domain.SubscribeDto;
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
		if(dto.getProfile() == null) {
			dto.setProfile("profile_default.png");
		}
		if(dto.getBanner() == null) {
			dto.setBanner("profile_banner_default.png");
		}
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
	
	@ApiOperation(value = "Nickname 중복 체크하기(true : 사용가능, false : 사용불가능)")
	@GetMapping(value = "/nickname-check/{mbr_nickname}")
	public ResponseEntity<Boolean> isDuplicationNickname(@PathVariable("mbr_nickname") String nickname){
		int res = userService.checkByNickname(nickname);
		if(res == 0) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}
	
	@ApiOperation(value = "비밀번호 변경하기")
	@PatchMapping(value = "/password")
	public ResponseEntity<String> changePassword(@ApiIgnore @RequestHeader("X-USERNO") String no, @RequestBody Map<String, String> map){
		int res = userService.updatePassword(map.get("password"), Integer.parseInt(no));
		if(res == 0) {
			return ResponseEntity.ok("변경 실패");
		}
		return ResponseEntity.ok("변경 성공");
	}
	
	@ApiOperation(value = "채널 구독 목록 가져오기")
	@GetMapping(value = "/like")
	public ResponseEntity<List<SubscribeDto>> channelGetLike(@ApiIgnore @RequestHeader("X-USERNO") String no){
		List<SubscribeDto> res = userService.subscribeList(Integer.parseInt(no));
		return ResponseEntity.ok(res);
	}
	
	@ApiOperation(value = "채널 구독하기")
	@PostMapping(value = "/like")
	public ResponseEntity<String> channelLike(@ApiIgnore @RequestHeader("X-USERNO") String no, @RequestBody Map<String, Integer> map){
		int res = userService.subscribe(Integer.parseInt(no), map.get("mbr_no"));
		if(res == 0) {
			return ResponseEntity.ok("구독 실패");
		}
		return ResponseEntity.ok("구독 성공");
	}
	
	@ApiOperation(value = "채널 구독 취소하기")
	@DeleteMapping(value = "/like")
	public ResponseEntity<String> channelDislike(@ApiIgnore @RequestHeader("X-USERNO") String no, @RequestBody Map<String, Integer> map){
		int res = userService.subscribeCancle(Integer.parseInt(no), map.get("mbr_no"));
		if(res == 0) {
			return ResponseEntity.ok("구독 취소 실패");
		}
		return ResponseEntity.ok("구독 취소 성공");
	}
	
	@ApiOperation(value = "유저 아이디 검색 정보 반환")
	@GetMapping(value = "/users/{user_id}")
	public ResponseEntity<UserDto> getUserInfo(@PathVariable("user_id") String userId){
		UserDto user = userService.findByUserId(userId);
		return ResponseEntity.ok(user);
	}
	
	@ApiOperation(value = "유저 아이디 검색 정보 반환")
	@GetMapping(value = "/like/{to}")
	public ResponseEntity<Boolean> channeldSubscribeCheck(@ApiIgnore @RequestHeader("X-USERNO") String from, @PathVariable("to") int to){
		int res = userService.subscribeCheck(Integer.parseInt(from), to);
		if(res == 0) {
			return ResponseEntity.ok(false);
		}
		return ResponseEntity.ok(true);
	}
	
}
