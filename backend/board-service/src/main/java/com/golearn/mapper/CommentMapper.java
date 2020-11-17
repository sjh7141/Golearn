package com.golearn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.golearn.domain.CommentDto;

@Mapper
public interface CommentMapper {

	public int findCommentCount(@Param("brdNo") int brdNo, @Param("parentNo") int parentNo );
	public List<CommentDto> findByCommentNo(@Param("brdNo") int brdNo, @Param("parentNo") int parentNo, @Param("startIndex") int startIndex, @Param("perPageNum") int perPageNum);
	public CommentDto findByComment(int cmtNo);
	public int findDetailsCount(int parentNo);
	public int registComment(CommentDto dto);
	public int updateComment(CommentDto dto);
	public int deleteComment(@Param("cmtNo") int cmtNo, @Param("mbrNo") int mbrNo);
}
