package com.golearn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.golearn.domain.Course;
import com.golearn.service.LikeService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class LikeController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LikeService likeService;
	
	// 좋아요
	@RequestMapping(method = RequestMethod.POST, value = "/like/{cos_no}")
	@ApiOperation(value = "코스 좋아요")
	public ResponseEntity insertLike(@ApiIgnore @RequestHeader(value = "X-USERNO") String mbrNo, @PathVariable("cos_no") long cosNo) {
		logger.info(">> LOAD insertLike <<");
		likeService.save(mbrNo, cosNo);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	// 좋아요 취소
	@RequestMapping(method = RequestMethod.DELETE, value = "/like/{cos_no}")
	@ApiOperation(value = "코스 좋아요 취소")
	public ResponseEntity deleteLike(@ApiIgnore @RequestHeader(value = "X-USERNO") String mbrNo, @PathVariable("cos_no") long cosNo) {
		logger.info(">> LOAD deleteLike <<");
		likeService.delete(mbrNo, cosNo);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "유저가 좋아요한 코스 목록 가져오기")
	@GetMapping(value = "/like")
	public ResponseEntity<List<Course>> getLikeCourse(@ApiIgnore @RequestHeader("X-USERNO") String no){
		List<Course> course = likeService.findByLike(Integer.parseInt(no));
		return ResponseEntity.ok(course);
	}
	
	@ApiOperation(value = "유저가 수강중인 코스 목록 가져오기")
	@GetMapping(value = "/view")
	public ResponseEntity<List<Course>> getViewCourse(@ApiIgnore @RequestHeader("X-USERNO") String no){
		List<Course> course = likeService.findByViewer(Integer.parseInt(no));
		return ResponseEntity.ok(course);
	}
	
	@ApiOperation(value = "해당 코스 좋아요 했는지 쳌쳌")
	@GetMapping(value = "/like/{cos_no}")
	public ResponseEntity<Boolean> checkCourseLike(@ApiIgnore @RequestHeader("X-USERNO") String no, @PathVariable("cos_no") int cosNo){
		int res = likeService.checkLike(Integer.parseInt(no), cosNo);
		if(res == 0) {
			return ResponseEntity.ok(false);
		}
		return ResponseEntity.ok(true);
	}
	
}
