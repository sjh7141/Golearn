package com.golearn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.golearn.domain.BoardDto;

@Mapper
public interface BoardMapper {

	public int registBoard(BoardDto dto);
	public int updateBoard(BoardDto dto);
	public void increaseView(int brdNo);
	public int deleteBoard(@Param("brdNo") int brdNo, @Param("mbrNo") int mbrNo);
	public List<BoardDto> findBoardList(@Param("type") int type, @Param("startIndex") int startIndex, @Param("perPageNum") int perPageNum);
	public BoardDto findBoardDetails(int brdNo);
	public int findBoardCount(int type);
	public int checkAdmin(int userNo);
}
