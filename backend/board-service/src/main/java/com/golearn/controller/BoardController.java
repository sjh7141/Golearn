package com.golearn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.golearn.domain.BoardDto;
import com.golearn.domain.PageDto;
import com.golearn.service.BoardService;

import io.swagger.annotations.ApiOperation;

@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@ApiOperation(value = "게시글 목록 조회하기 (1: 공지사항, 2: FAQ, 3: 질문게시판, 4: 자유게시판)")
	@GetMapping(value = "/{type}")
	public ResponseEntity<Map<String, Object>> getBoardList(@PathVariable("type") int type, @RequestParam("page_no") int pageNo){
		PageDto page = new PageDto(pageNo);
		page.setTotalCount(boardService.findBoardCount(type));
		
		List<BoardDto> board = boardService.findBoardList(type, page.getStartIndex(), page.getPerPageNum());
		
		Map<String, Object> map = new HashMap<>();
		map.put("board", board);
		map.put("page", page);
		return ResponseEntity.ok(map);
	}
}
