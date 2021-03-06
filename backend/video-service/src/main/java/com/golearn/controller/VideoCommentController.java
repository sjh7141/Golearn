package com.golearn.controller;

import com.golearn.model.VideoComment;
import com.golearn.model.VideoCommentPayload;
import com.golearn.service.VideoCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@Slf4j
@Api("VideoComment")
public class VideoCommentController {

    private final VideoCommentService videoCommentService;

    VideoCommentController(VideoCommentService videoCommentService) {
        this.videoCommentService = videoCommentService;
    }

    @ApiOperation(value = "댓글 상세")
    @GetMapping("/comment/details/{vid_cmt_no}")
    public ResponseEntity<VideoComment> getVideoComment(@PathVariable("vid_cmt_no") int vidCmtNo) {
        return new ResponseEntity(videoCommentService.getVideoComment(vidCmtNo), HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 조회")
    @GetMapping("/comment/{vid_no}")
    public ResponseEntity<List<VideoCommentPayload>> getVideoComments(@PathVariable("vid_no") int vidNo, @RequestParam(value = "page_no", required = false, defaultValue = "1") int pageNo) {
        return new ResponseEntity(videoCommentService.getVideoComments(vidNo, pageNo < 1 ? 0 : pageNo - 1), HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 쓰기")
    @PostMapping("/comment/{vid_no}")
    public ResponseEntity saveVideoComment(@ApiIgnore @RequestHeader("X-USERNO") int mbrNo, @PathVariable("vid_no") int vidNo, @RequestBody VideoComment videoComment) {
        videoComment.setVidNo(vidNo);
        videoComment.setMbrNo(mbrNo);
        VideoComment comment = videoCommentService.saveVideoComment(videoComment);
        return ResponseEntity.ok(comment);
//        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation(value = "댓글 수정")
    @PutMapping("/comment/{vid_cmt_no}")
    public ResponseEntity updateVideoComment(@ApiIgnore @RequestHeader("X-USERNO") int mbrNo, @PathVariable("vid_cmt_no") int vidCmtNo, @RequestBody VideoComment videoComment) {
        videoComment.setVidCmtNo(vidCmtNo);
        VideoComment comment = videoCommentService.updateVideoComment(videoComment, mbrNo);
        return ResponseEntity.ok(comment);
//        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping("/comment/{vid_cmt_no}")
    public ResponseEntity removeVideoComment(@ApiIgnore @RequestHeader("X-USERNO") int mbrNo, @PathVariable("vid_cmt_no") int vidCmtNo) {
        videoCommentService.removeVideoComment(vidCmtNo, mbrNo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "대댓글 쓰기")
    @PostMapping("/comment/{vid_no}/{vid_cmt_pno}")
    public ResponseEntity saveVideoReply(@ApiIgnore @RequestHeader("X-USERNO") int mbrNo, @PathVariable("vid_no") int vidNo, @PathVariable("vid_cmt_pno") int vidCmtNo, @RequestBody VideoComment videoComment) {
        videoComment.setMbrNo(mbrNo);
        videoComment.setVidCmtPno(vidCmtNo);
        videoComment.setVidNo(vidNo);
        VideoComment comment = videoCommentService.saveVideoComment(videoComment);
        return ResponseEntity.ok(comment);
//        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation(value = "대댓글 조회")
    @GetMapping("/comment/{vid_no}/{vid_cmt_pno}")
    public ResponseEntity<List<VideoComment>> getVideoReplies(@PathVariable("vid_no") int vidNo, @PathVariable("vid_cmt_pno") int vidCmtNo) {
        return new ResponseEntity(videoCommentService.getVideoReplies(vidCmtNo), HttpStatus.OK);
    }

    @ApiOperation(value = "대댓글 수정")
    @PutMapping("/comment/{vid_no}/{vid_cmt_pno}/{vid_cmt_no}")
    public ResponseEntity updateVideoReply(@ApiIgnore @RequestHeader("X-USERNO") int mbrNo, @PathVariable("vid_no") int vidNo, @PathVariable("vid_cmt_pno") int vidCmtPno, @PathVariable("vid_cmt_no") int vidCmtNo, @RequestBody VideoComment videoComment) {
        videoComment.setVidCmtNo(vidCmtNo);
        VideoComment comment = videoCommentService.updateVideoComment(videoComment, mbrNo);
        return ResponseEntity.ok(comment);
//        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "대댓글 삭제")
    @DeleteMapping("/comment/{vid_no}/{vid_cmt_pno}/{vid_cmt_no}")
    public ResponseEntity removeVideoReply(@ApiIgnore @RequestHeader("X-USERNO") int mbrNo, @PathVariable("vid_no") int vidNo, @PathVariable("vid_cmt_pno") int vidCmtPno, @PathVariable("vid_cmt_no") int vidCmtNo) {
        videoCommentService.removeVideoComment(vidCmtNo, mbrNo);
        return new ResponseEntity(HttpStatus.OK);
    }

}
