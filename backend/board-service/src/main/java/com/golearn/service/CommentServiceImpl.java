package com.golearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golearn.domain.CommentDto;
import com.golearn.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public int findCommentCount(int brdNo, int parentNo) {
		return commentMapper.findCommentCount(brdNo, parentNo);
	}

	@Override
	public List<CommentDto> findByCommentNo(int brdNo, int parentNo, int startIndex, int perPageNum) {
		return commentMapper.findByCommentNo(brdNo, parentNo, startIndex, perPageNum);
	}

	@Override
	public int registComment(CommentDto dto) {
		return commentMapper.registComment(dto);
	}

	@Override
	public int updateComment(CommentDto dto) {
		return commentMapper.updateComment(dto);
	}

	@Override
	public int deleteComment(int cmtNo, int mbrNo) {
		return commentMapper.deleteComment(cmtNo, mbrNo);
	}

	@Override
	public CommentDto findByComment(int cmtNo) {
		return commentMapper.findByComment(cmtNo);
	}

}
