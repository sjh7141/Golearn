package com.golearn.service;

import java.util.List;
import java.util.Map;

import com.golearn.domain.LoadmapCourseDto;
import com.golearn.domain.LoadmapDto;

public interface LoadmapService {

	public int registLoadmap(LoadmapDto dto);
	public int updateLoadmap(Map<String, Object> map, int userNo);
	public int deleteLoadmap(int ldmNo, int mbrNo);
	public LoadmapDto findByLoadmap(int ldmNo);
	public List<LoadmapCourseDto> findByCourse(int ldmNo);
	public List<LoadmapCourseDto> findByUserNo(int userNo);
	public int updateInCourse(List<LoadmapCourseDto> list);
	public int deleteInCourse(List<LoadmapCourseDto> list);
	public int registInCourse(List<LoadmapCourseDto> list);
}
