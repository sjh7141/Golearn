package com.golearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golearn.domain.LoadmapCourseDto;
import com.golearn.domain.LoadmapDto;
import com.golearn.mapper.LoadmapMapper;

@Service
public class LoadmapServiceImpl implements LoadmapService{

	@Autowired
	private LoadmapMapper loadmapMapper;
	
	@Override
	public int registLoadmap(LoadmapDto dto) {
		return loadmapMapper.registLoadmap(dto);
	}

	@Override
	public int updateLoadmap(LoadmapDto dto) {
		return loadmapMapper.updateLoadmap(dto);
	}

	@Override
	public int deleteLoadmap(int ldmNo, int mbrNo) {
		return loadmapMapper.deleteLoadmap(ldmNo, mbrNo);
	}

	@Override
	public LoadmapDto findByLoadmap(int ldmNo) {
		return loadmapMapper.findByLoadmap(ldmNo);
	}

	@Override
	public List<LoadmapCourseDto> findByCourse(int ldmNo) {
		return loadmapMapper.findByCourse(ldmNo);
	}
	
	@Override
	public int updateInCourse(List<LoadmapCourseDto> list) {
		return loadmapMapper.updateInCourse(list);
	}

	@Override
	public int deleteInCourse(List<LoadmapCourseDto> list) {
		return loadmapMapper.deleteInCourse(list);
	}

	@Override
	public int registInCourse(List<LoadmapCourseDto> list) {
		return loadmapMapper.registInCourse(list);
	}

}
