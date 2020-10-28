package com.golearn.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;

import com.golearn.domain.Index;
import com.golearn.service.IndexService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class IndexController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IndexService indexService;
	
	// 목차 생성|수정|삭제
	@RequestMapping(method = RequestMethod.PUT, value = "/index")
	@ApiOperation(value = "목차 생성|수정|삭제")
	public ResponseEntity updateIndex(@ApiIgnore @RequestHeader(value = "X-USERNO") String mbrNo, @RequestBody HashMap<String, ArrayList<Index>> request) {
		logger.info(">> LOAD updateIndex <<");
		ArrayList<Index> insertIndexs = request.get("insert");
		ArrayList<Index> updateIndexs = request.get("update");
		ArrayList<Index> deleteIndexs = request.get("delete");
		
		indexService.saveAll(insertIndexs);
		indexService.updateAll(updateIndexs);
		indexService.deleteAll(deleteIndexs);
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	// 목차 목록 조회
	@RequestMapping(method = RequestMethod.GET, value = "/index/{cos_no}")
	@ApiOperation(value = "목차 목록 조회")
	public ResponseEntity<List<Index>> getAllIndex(@PathVariable("cos_no") long cosNo) {
		logger.info(">> LOAD getAllIndex <<");
		List<Index> response = indexService.findByCosNo(cosNo);
		return new ResponseEntity<List<Index>>(response, HttpStatus.OK);
	}
	
	// 목차 상세 정보
	@RequestMapping(method = RequestMethod.GET, value = "/index/detail/{idx_no}")
	@ApiOperation(value = "목차 상세 정보")
	public ResponseEntity<Index> getIndex(@PathVariable("idx_no") long idxNo){
		logger.info(">> LOAD getIndex <<");
		Index response = indexService.findById(idxNo);
		return new ResponseEntity<Index>(response, HttpStatus.OK);
	}
}
