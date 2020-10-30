package com.golearn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.golearn.domain.LoadmapCourseDto;
import com.golearn.domain.LoadmapDto;

@Mapper
public interface LoadmapMapper {

	public int registLoadmap(LoadmapDto dto);
	public int updateLoadmap(LoadmapDto dto);
	public int deleteLoadmap(@Param("ldmNo")int ldmNo, @Param("mbrNo") int mbrNo);
	public LoadmapDto findByLoadmap(int ldmNo);
	public List<LoadmapCourseDto> findByCourse(int ldmNo);
	public int updateInCourse(List<LoadmapCourseDto> list);
	public int deleteInCourse(List<LoadmapCourseDto> list);
	public int registInCourse(List<LoadmapCourseDto> list);
}
