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
	public List<ChannelDto> findChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LoadmapDto> findLoadmap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseDto> findCource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByVideo(String search) {
		return searchMapper.countByVideo(search);
	}
}
