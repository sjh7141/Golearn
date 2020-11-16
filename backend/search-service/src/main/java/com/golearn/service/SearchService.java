package com.golearn.service;

import java.util.List;

import com.golearn.domain.ChannelDto;
import com.golearn.domain.CourseDto;
import com.golearn.domain.LoadmapDto;
import com.golearn.domain.TagDto;
import com.golearn.domain.VideoDto;

public interface SearchService {

	public List<TagDto> findTag();
	public List<VideoDto> findVideo(String search, int startIndex, int perPageNum, List<Integer> tag);
	public int countByVideo(String search, List<Integer> tag);
	public List<TagDto>findTagByVideo(List<Integer> list);
	public List<ChannelDto> findChannel(String search, int startIndex, int perPageNum);
	public int countByChannel(String search);
	public List<LoadmapDto> findLoadmap(String search, int startIndex, int perPageNum);
	public int countByLoadmap(String search);
	public List<CourseDto> findCourse(String search, int startIndex, int perPageNum);
	public int countByCourse(String search);
	public List<TagDto>findTagByCourse(List<Integer> list);
}
