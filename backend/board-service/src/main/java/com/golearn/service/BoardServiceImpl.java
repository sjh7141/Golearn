package com.golearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.golearn.domain.BoardDto;
import com.golearn.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public int registBoard(BoardDto dto) {
		return boardMapper.registBoard(dto);
	}

	@Override
	public int updateBoard(BoardDto dto) {
		return boardMapper.updateBoard(dto);
	}

	@Override
	public void increaseView(int brdNo) {
		boardMapper.increaseView(brdNo);
	}

	@Override
	public int deleteBoard(int brdNo, int mbrNo) {
		return boardMapper.deleteBoard(brdNo, mbrNo);
	}

	@Override
	public List<BoardDto> findBoardList(int type, int startIndex, int perPageNum) {
		return boardMapper.findBoardList(type, startIndex, perPageNum);
	}

	@Override
	@Transactional
	public BoardDto findBoardDetails(int brdNo) {
		increaseView(brdNo);
		return boardMapper.findBoardDetails(brdNo);
	}

	@Override
	public int findBoardCount(int type) {
		return boardMapper.findBoardCount(type);
	}

}
