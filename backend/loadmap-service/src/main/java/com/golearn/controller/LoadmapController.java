package com.golearn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.golearn.domain.LoadmapCourseDto;
import com.golearn.domain.LoadmapDto;
import com.golearn.service.LoadmapService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class LoadmapController {
	
	@Autowired
	private LoadmapService loadmapService;

	@ApiOperation(value = "로드맵 생성하기 (껍데기만)")
	@PostMapping(value = "/")
	public ResponseEntity<Integer> makeLoadmap(@ApiIgnore @RequestHeader("X-USERNO") String userNo) {
		LoadmapDto dto = new LoadmapDto();
		dto.setMbrNo(Integer.parseInt(userNo));
		int res = loadmapService.registLoadmap(dto);
		
		return ResponseEntity.ok(dto.getLdmNo());
	}
	
	@ApiOperation(value = "로드맵 수정하기 (찐생성)")
	@PutMapping(value = "/")
	public ResponseEntity<String> modifyLoadmap(@ApiIgnore @RequestHeader("X-USERNO") String userNo, @RequestBody Map<String, Object> map){
		int res = loadmapService.updateLoadmap(map, Integer.parseInt(userNo));
		if(res == 0) {
			return ResponseEntity.ok("등록 실패");
		}
		return ResponseEntity.ok("등록 성공");
	}
	
	@ApiOperation(value = "로드맵 정보 가져오기")
	@GetMapping(value = "/{ldm_no}")
	public ResponseEntity<Map<String, Object>> getLoadmapInfo(@PathVariable("ldm_no") int ldmNo){
		Map<String, Object> map = new HashMap<>();
		LoadmapDto loadmap = loadmapService.findByLoadmap(ldmNo);
		List<LoadmapCourseDto> course = loadmapService.findByCourse(ldmNo);
		
		map.put("loadmap", loadmap);
		map.put("course", course);
		
		return ResponseEntity.ok(map);
	}
	
	@ApiOperation(value = "로드맵 삭제하기")
	@DeleteMapping(value = "/")
	public ResponseEntity<String> removeLoadmap(@ApiIgnore @RequestHeader("X-USERNO") String userNo, @RequestBody Map<String, Object> map){
		int ldmNo = (int)map.get("	");
		int res = loadmapService.deleteLoadmap(ldmNo, Integer.parseInt(userNo));
		if(res == 0) {
			return ResponseEntity.ok("삭제 실패");
		}
		return ResponseEntity.ok("삭제 성공");
	}
	
	@ApiOperation(value = "유저가 만든 로드맵 가져오기")
	@GetMapping(value = "/member/{mbr_no}")
	public ResponseEntity<List<LoadmapCourseDto>> getLoadmapUser(@PathVariable("mbr_no") int userNo){
		List<LoadmapCourseDto> loadmap = loadmapService.findByUserNo(userNo);
		
		return ResponseEntity.ok(loadmap);
	}
}
