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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.golearn.domain.CommentDto;
import com.golearn.domain.PageDto;
import com.golearn.service.CommentService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@ApiOperation(value = "댓글 불러오기")
	@GetMapping(value = "/{brd_no}")
	public ResponseEntity<Map<String, Object>> getCommentList(@PathVariable("brd_no") int brdNo, @RequestParam("page_no") int pageNo){
		PageDto page = new PageDto(pageNo);
		page.setTotalCount(commentService.findCommentCount(brdNo, 0));
		List<CommentDto> comment = commentService.findByCommentNo(brdNo, 0, page.getStartIndex(), page.getPerPageNum());
		
		Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("comment", comment);
		
		return ResponseEntity.ok(map);
	}
	
	@ApiOperation(value = "대댓글 불러오기")
	@GetMapping(value = "/{brd_no}/{brd_cmt_pno}")
	public ResponseEntity<Map<String, Object>> getCommentDetailsList(@PathVariable("brd_no") int brdNo, @PathVariable("brd_cmt_pno") int parentNo, @RequestParam("page_no") int pageNo){
		PageDto page = new PageDto(pageNo);
		page.setTotalCount(commentService.findCommentCount(brdNo, parentNo));
		List<CommentDto> comment = commentService.findByCommentNo(brdNo, parentNo, page.getStartIndex(), page.getPerPageNum());
		
		Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("comment", comment);
		
		return ResponseEntity.ok(map);
	}
	
	@ApiOperation(value = "댓글 추가하기( parent_no : 0 이면 댓글, 아니면 대댓글")
	@PostMapping(value = "/")
	public ResponseEntity<String> registComment(@ApiIgnore @RequestHeader("X-USERNO") String userNo, @RequestBody CommentDto dto){
		dto.setMbrNo(Integer.parseInt(userNo));
		int res = commentService.registComment(dto);
		if(res == 0) {
			return ResponseEntity.ok("등록 실패");
		}
		return ResponseEntity.ok("등록 성공");
	}
	
	@ApiOperation(value = "댓글 수정하기")
	@PutMapping(value = "/")
	public ResponseEntity<String> modifyComment(@ApiIgnore @RequestHeader("X-USERNO") String userNo, @RequestBody CommentDto dto){
		dto.setMbrNo(Integer.parseInt(userNo));
		int res = commentService.updateComment(dto);
		if(res == 0) {
			return ResponseEntity.ok("수정 실패");
		}
		return ResponseEntity.ok("수정 성공");
	}
}
