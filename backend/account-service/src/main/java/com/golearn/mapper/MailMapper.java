package com.golearn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MailMapper {

	public int saveMessage(@Param("userNo") int userNo, @Param("message") String message);
	public int checkMessage(@Param("userNo") int userNo, @Param("message") String message);
	public void updateCheckMail(int userNo);
}
