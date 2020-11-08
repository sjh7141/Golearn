package com.golearn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.golearn.domain.Course;

@Mapper
public interface CourseMapper {

	public List<Course> findByLike(int userNo);
	public List<Course> findByViewer(int userNo);
}
