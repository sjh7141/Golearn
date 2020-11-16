package com.golearn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.golearn.domain.Course;
import com.golearn.dto.VideoRequestDto;

@Mapper
public interface CourseMapper {

	public List<Course> findByLike(int userNo);
	public List<Course> findByViewer(int userNo);
	public int checkLike(@Param("userNo") int userNo, @Param("cosNo") int cosNo);
	public int checkIndex(@Param("userNo") int userNo, @Param("idxNo") int idxNo);
	public VideoRequestDto findByRequestNo(@Param("vidReqNo") int vidReqNo);
	public int findCourseLikeCount(long cosNo);
	public int findCourseViewerCount(long cosNo);
}
