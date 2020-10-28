package com.golearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
