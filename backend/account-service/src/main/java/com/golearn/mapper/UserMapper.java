package com.golearn.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.golearn.domain.UserDto;

@Mapper
public interface UserMapper {

	public int save(UserDto dto);
	public int updateUser(UserDto dto);
	public int disableUser(int no);
	public UserDto findByUsername(int no);
	public int checkByUsername(String username);
	public int chekcByEmail(String email);
	public int checkByNickname(String nickname);
	public int updatePassword(String password, int no);
	public int subscribe(int from, int to);
	public int subscribeCancle(int from, int to);
}
