package com.golearn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.golearn.domain.ChannelDto;
import com.golearn.domain.CourseDto;
import com.golearn.domain.LoadmapDto;
import com.golearn.domain.TagDto;
import com.golearn.domain.VideoDto;

@Mapper
public interface SearchMapper {

	public List<TagDto> findTag();
	public List<VideoDto> findVideo(@Param("search") String search, @Param("startIndex") int startIndex, @Param("perPageNum")int perPageNum);
	public int countByVideo(String search);
	public List<ChannelDto> findChannel();
	public List<LoadmapDto> findLoadmap();
	public List<CourseDto> findCource();
}
