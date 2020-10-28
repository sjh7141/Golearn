package com.golearn.service;

import java.util.List;

import com.golearn.domain.ChannelDto;
import com.golearn.domain.CourseDto;
import com.golearn.domain.LoadmapDto;
import com.golearn.domain.TagDto;
import com.golearn.domain.VideoDto;

public interface SearchService {

	public List<TagDto> findTag();
	public List<VideoDto> findVideo(String search, int startIndex, int perPageNum);
	public int countByVideo(String search);
	public List<ChannelDto> findChannel();
	public List<LoadmapDto> findLoadmap();
	public List<CourseDto> findCource();
}
