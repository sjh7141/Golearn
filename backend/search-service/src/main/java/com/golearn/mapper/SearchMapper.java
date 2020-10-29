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
	public List<ChannelDto> findChannel(@Param("search") String search, @Param("startIndex") int startIndex, @Param("perPageNum")int perPageNum);
	public int countByChannel(String search);
	public List<LoadmapDto> findLoadmap(@Param("search") String search, @Param("startIndex") int startIndex, @Param("perPageNum")int perPageNum);
	public int countByLoadmap(String search);
	public List<CourseDto> findCourse(@Param("search") String search, @Param("startIndex") int startIndex, @Param("perPageNum")int perPageNum);
	public int countByCourse(String search);
	public List<TagDto>findTagByCourse(List<Integer> list);
}
