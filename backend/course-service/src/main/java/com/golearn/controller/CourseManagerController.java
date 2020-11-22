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

import com.golearn.domain.CourseManager;
import com.golearn.domain.CourseManagerResopnse;
import com.golearn.service.CourseManagerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class CourseManagerController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseManagerService courseManagerService;

	// 코스 관리자 생성
	@RequestMapping(method = RequestMethod.POST, value = "/manager")
	@ApiOperation(value = "코스 관리자 생성")
	public ResponseEntity insertCourseManager(@ApiIgnore @RequestHeader(value = "X-USERNO") String mbrNo,
			@RequestBody CourseManager request) {
		logger.info(">> LOAD insertCourseManager <<");
		if(courseManagerService.checkManager(request.getCosNo(), Long.parseLong(mbrNo)) == 0) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		courseManagerService.save(request);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	// 코스 관리자 삭제
	@RequestMapping(method = RequestMethod.DELETE, value = "/manager/{cos_no}/{manager_no}")
	@ApiOperation(value = "코스 관리자 삭제")
	public ResponseEntity deleteCourseManager(@ApiIgnore @RequestHeader(value = "X-USERNO") String mbrNo,
			@PathVariable("cos_no") int cosNo, @PathVariable("manager_no") int managerNo) {
		logger.info(">> LOAD deleteCourseManager <<");
		CourseManager request = new CourseManager();
		request.setCosNo(cosNo);
		request.setMbrNo(managerNo);
		if(courseManagerService.checkManager(request.getCosNo(), Long.parseLong(mbrNo)) == 0) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		courseManagerService.delete(request);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	// 코스 관리자 목록
	@RequestMapping(method = RequestMethod.GET, value = "/manager/{cos_no}")
	@ApiOperation(value = "코스 관리자 목록")
	public ResponseEntity<List<CourseManagerResopnse>> getManagers(@PathVariable("cos_no") long cosNo) {
		logger.info(">> LOAD getManagers <<");
		List<CourseManagerResopnse> response = courseManagerService.findManagers(cosNo);
		return new ResponseEntity<List<CourseManagerResopnse>>(response, HttpStatus.OK);
	}

	// 코스관리자를 추가하기 위한 검색
	@RequestMapping(method = RequestMethod.GET, value = "/manager/{cos_no}/search")
	@ApiOperation(value = "코스 관리자를 추가하기 위한 검색")
	public ResponseEntity<List<CourseManagerResopnse>> searchToAddManagers(@PathVariable("cos_no") long cosNo,
			@ApiParam(required = false, value = "사용자 ID 검색") @RequestParam(required = false, value = "search", defaultValue = "") String search) {
		logger.info(">> LOAD searchToAddManagers <<");
		List<CourseManagerResopnse> response = courseManagerService.searchToAddManagers(cosNo, search);
		return new ResponseEntity<List<CourseManagerResopnse>>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "현재 코스 관리자 인지 아닌지 쳌")
	@GetMapping(value = "/manager/check/{cos_no}")
	public ResponseEntity<Boolean> checkManager(@ApiIgnore @RequestHeader("X-USERNO") String no, @PathVariable("cos_no") int cosNo){
		int res = courseManagerService.checkManager(cosNo, Long.parseLong(no));
		if(res == 0) {
			return ResponseEntity.ok(false);
		}
		return ResponseEntity.ok(true);
	}
}
