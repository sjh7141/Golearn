package com.golearn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.golearn.domain.Course;
import com.golearn.service.CourseService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class CourseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseService courseService;

	// 20개씩 코스 조회
	@RequestMapping(method = RequestMethod.GET, value = "")
	@ApiOperation(value = "상위 20개 코스 조회")
	public ResponseEntity<List<Course>> getPerPageBy20(@RequestParam("page") int page) {
		logger.info(">> LOAD getPerPageBy20 <<");
		List<Course> response = courseService.perPageBy20(page);
		return new ResponseEntity<List<Course>>(response, HttpStatus.OK);
	}

	// 코스 상세 정보
	@RequestMapping(method = RequestMethod.GET, value = "{cos_no}")
	@ApiOperation(value = "코스 상세 정보 조회")
	public ResponseEntity<Course> getCourseDetail(@PathVariable("cos_no") int cosNo) {
		logger.info(">> LOAD getCourseDetail <<");
		Course response = courseService.findById(cosNo);
		return new ResponseEntity<Course>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "코스 상세 정보 조회")
	@GetMapping(value = "/member/{mbr_no}")
	public ResponseEntity<List<Course>> getCourseUser(@PathVariable("mbr_no") long mbrNo) {
		logger.info(">> LOAD getCourseDetail <<");
		List<Course> course = courseService.findByMbrNo(mbrNo);
		return ResponseEntity.ok(course);
	}

	// 코스 생성
	@RequestMapping(method = RequestMethod.POST, value = "")
	@ApiOperation(value = "코스 생성")
	public ResponseEntity<Course> createCourse(@ApiIgnore @RequestHeader(value = "X-USERNO") String mbrNo) {
		logger.info(">> LOAD createCourse <<");
		Course response = courseService.save(mbrNo);
		return new ResponseEntity<Course>(response, HttpStatus.CREATED);
	}

	// 코스 수정
	@RequestMapping(method = RequestMethod.PUT, value = "")
	@ApiOperation(value = "코스 수정")
	public ResponseEntity<Course> updateCourse(@ApiIgnore @RequestHeader(value = "X-USERNO") String mbrNo,
			@RequestBody Course courseDto) {
		logger.info(">> LOAD updateCourse <<");
		Course response = courseService.update(mbrNo, courseDto);
		return new ResponseEntity<Course>(response, HttpStatus.CREATED);
	}
}
