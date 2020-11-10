package com.golearn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.golearn.domain.ChannelDto;
import com.golearn.domain.CourseDto;
import com.golearn.domain.LoadmapDto;
import com.golearn.domain.PageDto;
import com.golearn.domain.TagDto;
import com.golearn.domain.VideoDto;
import com.golearn.service.SearchService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/v1")
public class SearchController {

	@Autowired
	private SearchService searchService;

	@ApiOperation(value = "검색 목록 가져오기")
	@GetMapping(value = "/search")
	public ResponseEntity<Map<String, Object>> search(@RequestParam("type") String type,
			@RequestParam("search") String search, @RequestParam("page_no") int pageNo, @RequestParam(required=false, value="tag_no") List<Integer> tag) {
		Map<String, Object> map = new HashMap<>();
		PageDto page = new PageDto(pageNo);
		if (search.equals(" ")) {
			search = "";
		}
		if (type.equals("video")) {
			page.setTotalCount(searchService.countByVideo(search, tag));
			List<VideoDto> video = searchService.findVideo(search, page.getStartIndex(), page.getPerPageNum(), tag);
			/*List<Integer> list = video.stream().map(el -> el.getVidNo()).collect(Collectors.toList());
			List<TagDto> tag = searchService.findTagByVideo(list);
			map.put("tag", tag);*/
			map.put("video", video);
		} else if (type.equals("course")) {
			page.setTotalCount(searchService.countByCourse(search));
			List<CourseDto> course = searchService.findCourse(search, page.getStartIndex(), page.getPerPageNum());
			/*List<Integer> list = course.stream().map(el -> el.getCosNo()).collect(Collectors.toList());
			List<TagDto> tag = searchService.findTagByCourse(list);
			map.put("tag", tag);*/
			map.put("course", course);
		} else if (type.equals("channel")) {
			page.setTotalCount(searchService.countByChannel(search));
			List<ChannelDto> channel = searchService.findChannel(search, page.getStartIndex(), page.getPerPageNum());
			map.put("channel", channel);
		} else if (type.equals("loadmap")) {
			page.setTotalCount(searchService.countByLoadmap(search));
			List<LoadmapDto> loadmap = searchService.findLoadmap(search, page.getStartIndex(), page.getPerPageNum());
			map.put("loadmap", loadmap);
		} 

		map.put("page", page);

		return ResponseEntity.ok(map);
	}
	
	@ApiOperation(value = "태그 목록 가져오기")
	@GetMapping(value = "/tag")
	public ResponseEntity<List<TagDto>> getTag(){
		List<TagDto> tag = searchService.findTag();
		return ResponseEntity.ok(tag);
	}
}
