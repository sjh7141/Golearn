package com.golearn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.golearn.domain.SubscribeDto;
import com.golearn.domain.UserDto;

@Mapper
public interface UserMapper {

	public int save(UserDto dto);
	public int updateUser(UserDto dto);
	public int disableUser(int no);
	public UserDto findByUsername(int no);
	public UserDto findByUserno(int no);
	public UserDto findByUserId(String userId);
	public int checkByUsername(String username);
	public int checkByEmail(String email);
	public int checkByNickname(String nickname);
	public int updatePassword(@Param("password") String password, @Param("no") int no);
	public int subscribe(@Param("from") int from, @Param("to") int to);
	public List<SubscribeDto> subscribeList(int no);
	public int subscribeCancle(@Param("from") int from, @Param("to") int to);
	public int subscribeCheck(@Param("from") int from, @Param("to") int to);
}
