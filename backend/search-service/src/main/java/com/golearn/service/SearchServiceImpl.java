package com.golearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golearn.domain.ChannelDto;
import com.golearn.domain.CourseDto;
import com.golearn.domain.LoadmapDto;
import com.golearn.domain.TagDto;
import com.golearn.domain.VideoDto;
import com.golearn.mapper.SearchMapper;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchMapper searchMapper;

	@Override
	public List<TagDto> findTag() {
		return searchMapper.findTag();
	}

	@Override
	public List<VideoDto> findVideo(String search, int startIndex, int perPageNum) {
		return searchMapper.findVideo(search, startIndex, perPageNum);
	}

	@Override
	public int countByVideo(String search) {
		return searchMapper.countByVideo(search);
	}

	@Override
	public List<ChannelDto> findChannel(String search, int startIndex, int perPageNum) {
		return searchMapper.findChannel(search, startIndex, perPageNum);
	}

	@Override
	public int countByChannel(String search) {
		return searchMapper.countByChannel(search);
	}

	@Override
	public List<LoadmapDto> findLoadmap(String search, int startIndex, int perPageNum) {
		return searchMapper.findLoadmap(search, startIndex, perPageNum);
	}

	@Override
	public int countByLoadmap(String search) {
		return searchMapper.countByLoadmap(search);
	}

	@Override
	public List<CourseDto> findCourse(String search, int startIndex, int perPageNum) {
		return searchMapper.findCourse(search, startIndex, perPageNum);
	}

	@Override
	public int countByCourse(String search) {
		return searchMapper.countByCourse(search);
	}
}
