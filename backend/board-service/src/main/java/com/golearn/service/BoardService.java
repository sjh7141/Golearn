package com.golearn.service;

import java.util.List;

import com.golearn.domain.BoardDto;

public interface BoardService {

	public int registBoard(BoardDto dto);
	public int updateBoard(BoardDto dto);
	public void increaseView(int brdNo);
	public int deleteBoard(int brdNo, int mbrNo);
	public List<BoardDto> findBoardList(int type, int startIndex, int perPageNum);
	public BoardDto findBoardDetails(int brdNo);
	public int findBoardCount(int type);
}
