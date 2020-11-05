package com.golearn.service;

import java.util.List;

import com.golearn.domain.SubscribeDto;
import com.golearn.domain.UserDto;

public interface UserService {

	public int save(UserDto dto);
	public int updateUser(UserDto dto);
	public int disableUser(int no);
	public UserDto findByUsername(int no);
	public UserDto findByUserId(String userId);
	public int checkByUsername(String username);
	public int checkByEmail(String email);
	public int checkByNickname(String nickname);
	public int updatePassword(String password, int no);
	public int subscribe(int from, int to);
	public List<SubscribeDto> subscribeList(int no);
	public int subscribeCancle(int from, int to);
	public int subscribeCheck(int from, int to);
	
}
