package com.golearn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.golearn.domain.VideoManager;
import com.golearn.domain.VideoVersioningResopnse;
import com.golearn.service.VideoManagerService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class VideoManagerController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	VideoManagerService videoManagerService;

	// 영상 요청 하기
	@RequestMapping(method = RequestMethod.POST, value = "/video")
	@ApiOperation(value = "영상 요청 하기")
	public ResponseEntity<VideoManager> requestVideo(@ApiIgnore @RequestHeader(value = "X-USERNO") String mbrNo,
			@RequestBody VideoManager request) {
		logger.info(">> LOAD requestVideo <<");
		VideoManager response = videoManagerService.requestVideo(mbrNo, request);
		return new ResponseEntity<VideoManager>(response, HttpStatus.CREATED);
	}

	// 영상 요청 승인|거부
	@RequestMapping(method = RequestMethod.PUT, value = "/video")
	@ApiOperation(value = "영상 요청 승인|거부")
	public ResponseEntity<VideoManager> updateVideo(@ApiIgnore @RequestHeader(value = "X-USERNO") String mbrNo,
			@RequestBody VideoManager request) {
		logger.info(">> LOAD updateVideo <<");
		VideoManager response = videoManagerService.updateVideo(mbrNo, request);
		return new ResponseEntity<VideoManager>(response, HttpStatus.CREATED);
	}

	// 영상 요청 목록
	@RequestMapping(method = RequestMethod.GET, value = "/video/{cos_no}")
	@ApiOperation(value = "영상 요청 목록")
	public ResponseEntity<List<VideoManager>> getRequestVideos(
			@ApiIgnore @RequestHeader(value = "X-USERNO") String mbrNo, @PathVariable("cos_no") long cosNo,
			@RequestParam("page") int page) {
		logger.info(">> LOAD getRequestVideos <<");
		List<VideoManager> response = videoManagerService.perPageBy20(cosNo, page);
		return new ResponseEntity<List<VideoManager>>(response, HttpStatus.CREATED);
	}

	// 버저닝 목록 가져오기
	@RequestMapping(method = RequestMethod.GET, value = "/versions/{idx_no}")
	@ApiOperation(value = "영상 버저닝 목록")
	public ResponseEntity<List<VideoVersioningResopnse>> getVideoVersioning(@PathVariable("idx_no") long idxNo) {
		logger.info(">> LOAD getRequestVideos <<");
		List<VideoVersioningResopnse> response = videoManagerService.getVersioning(idxNo);
		return new ResponseEntity<List<VideoVersioningResopnse>>(response, HttpStatus.CREATED);
	}
}
