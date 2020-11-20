package com.golearn.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.golearn.dto.Tag;
import com.golearn.service.CourseManagerService;
import com.golearn.service.TagService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/tag")
public class TagController {

	@Autowired
	private TagService tagService;
	
	@Autowired
	CourseManagerService courseManagerService;
	
	@ApiOperation(value = "코스 태그 가져오기")
	@GetMapping(value = "/{cos_no}")
	public ResponseEntity<List<Tag>> getTagList(@PathVariable("cos_no") int cosNo){
		List<Tag> tags = tagService.findByCosNo(cosNo);
		return ResponseEntity.ok(tags);
	}
	
	@ApiOperation(value = "코스 태그 등록하기")
	@PostMapping(value = "/")
	public ResponseEntity<String> registTag(@RequestBody Map<String, Object> map, @ApiIgnore @RequestHeader(value = "X-USERNO") String mbrNo){
		int cosNo = (int)map.get("cos_no");
		if(courseManagerService.checkManager(cosNo, Long.parseLong(mbrNo)) == 0) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		List<Integer> list = (List<Integer>)map.get("list");
		int res = tagService.insertTag(cosNo, list);
		if(res == 0) {
			return ResponseEntity.ok("등록 실패");
		}
		return ResponseEntity.ok("등록 성공");
	}
	
	@ApiOperation(value = "코스 태그 삭제")
	@DeleteMapping(value = "/{cos_no}/{list}")
	public ResponseEntity<String> removeTag(@PathVariable("cos_no") int cosNo, @PathVariable("list") List<Integer> list, @ApiIgnore @RequestHeader(value = "X-USERNO") String mbrNo){
		if(courseManagerService.checkManager(cosNo, Long.parseLong(mbrNo)) == 0) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		int res = tagService.deleteTag(cosNo, list);
		if(res == 0) {
			return ResponseEntity.ok("삭제 실패");
		}
		return ResponseEntity.ok("삭제 성공"); 
	}
}
