package com.golearn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.golearn.domain.CommentDto;

@Mapper
public interface CommentMapper {

	public List<CommentDto> findByBoardNo(@Param("brdNo") int brdNo, @Param("startIndex") int startIndex, @Param("perPageNum") int perPageNum);
	public List<CommentDto> findByCommentNo(@Param("cmtNo") int cmtNo, @Param("startIndex") int startIndex, @Param("perPageNum") int perPageNum);
	public int registComment(CommentDto dto);
	public int updateComment(CommentDto dto);
	public int deleteComment(int cmtNo);
}
