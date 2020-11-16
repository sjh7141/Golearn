package com.golearn.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.golearn.domain.LoadmapCourseDto;
import com.golearn.domain.LoadmapDto;
import com.golearn.mapper.LoadmapMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class LoadmapServiceImpl implements LoadmapService{

	@Autowired
	private LoadmapMapper loadmapMapper;
	
	@Override
	public int registLoadmap(LoadmapDto dto) {
		return loadmapMapper.registLoadmap(dto);
	}

	@Override
	@Transactional
	public int updateLoadmap(Map<String, Object> map, int userNo) {
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		LoadmapDto loadmap = mapper.convertValue(map.get("loadmap"), LoadmapDto.class);
		loadmap.setMbrNo(userNo);
		if(loadmap.getLdmThumbnail() == null) {
			loadmap.setLdmThumbnail("https://go-learn.s3.ap-northeast-2.amazonaws.com/member/banner/profile_banner_default.png");
		}
		
		List<LoadmapCourseDto> insert = gson.fromJson(map.get("insert").toString(), new TypeToken<List<LoadmapCourseDto>>() {}.getType());
		List<LoadmapCourseDto> update = gson.fromJson(map.get("update").toString(), new TypeToken<List<LoadmapCourseDto>>() {}.getType());
		List<LoadmapCourseDto> delete = gson.fromJson(map.get("delete").toString(), new TypeToken<List<LoadmapCourseDto>>() {}.getType());
		
		int res = loadmapMapper.updateLoadmap(loadmap);
		
		if (delete.size() != 0) {
			deleteInCourse(delete);
		}
		if (update.size() != 0) {
			updateInCourse(update);
		}
		if (insert.size() != 0) {
			registInCourse(insert);
		}

		return res;
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

	@Override
	public List<LoadmapCourseDto> findByUserNo(int userNo) {
		return loadmapMapper.findByUserNo(userNo);
	}

}
