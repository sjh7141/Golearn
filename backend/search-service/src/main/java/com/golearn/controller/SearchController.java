package com.golearn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.golearn.domain.CourseDto;
import com.golearn.domain.PageDto;
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
			@RequestParam("search") String search, @RequestParam("page_no") int pageNo) {
		Map<String, Object> map = new HashMap<>();
		PageDto page = new PageDto(pageNo);
		if(search.equals(" ")) {
			search = "";
		}
		if (type.equals("video")) {
			page.setTotalCount(searchService.countByVideo(search));
			List<VideoDto> video = searchService.findVideo(search, page.getStartIndex(), page.getPerPageNum());
			map.put("video", video);
		} else if (type.equals("course")) {
			page.setTotalCount(searchService.countByCourse(search));
			System.out.println(page.getStartIndex() + " " +page.getPerPageNum() + " "+ search);
			List<CourseDto> course = searchService.findCourse(search, page.getStartIndex(), page.getPerPageNum());
			map.put("course", course);
		}

		map.put("page", page);

		return ResponseEntity.ok(map);
	}
}
