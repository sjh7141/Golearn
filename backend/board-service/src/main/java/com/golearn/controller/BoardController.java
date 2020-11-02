package com.golearn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.golearn.domain.BoardDto;
import com.golearn.domain.PageDto;
import com.golearn.service.BoardService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

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
	
	@ApiOperation(value = "게시글 작성하기")
	@PostMapping(value = "/")
	public ResponseEntity<String> registBoard(@ApiIgnore @RequestHeader("X-USERNO") String userNo, @RequestBody BoardDto dto){
		dto.setMbrNo(Integer.parseInt(userNo));
		int res = boardService.registBoard(dto);
		
		if(res == 0) {
			return ResponseEntity.ok("등록 실패");
		}
		return ResponseEntity.ok("등록 성공");
	}
	
	@ApiOperation(value = "게시글 수정하기")
	@PutMapping(value = "/")
	public ResponseEntity<String> removeBoard(@ApiIgnore @RequestHeader("X-USERNO") String userNo, @RequestBody BoardDto dto){
		dto.setMbrNo(Integer.parseInt(userNo));
		int res = boardService.updateBoard(dto);
		
		if(res == 0) {
			return ResponseEntity.ok("수정 실패");
		}
		return ResponseEntity.ok("수정 성공");
	}
	
	@ApiOperation(value = "게시글 상세 보기")
	@GetMapping(value = "/details/{brdNo}")
	public ResponseEntity<BoardDto> getBoardDetails(@PathVariable("brdNo") int brdNo){
		BoardDto board = boardService.findBoardDetails(brdNo);
		return ResponseEntity.ok(board);
	}
}
