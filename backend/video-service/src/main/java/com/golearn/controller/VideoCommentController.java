package com.golearn.controller;

import com.golearn.model.VideoComment;
import com.golearn.model.VideoCommentPayload;
import com.golearn.service.VideoCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VideoCommentController {

    private VideoCommentService videoCommentService;

    VideoCommentController(VideoCommentService videoCommentService) {
        this.videoCommentService = videoCommentService;
    }

    @GetMapping("/comment/{vid_no}")
    public ResponseEntity<List<VideoCommentPayload>> getVideoComments(@PathVariable("vid_no") int vidNo, @RequestParam(value = "page_no", required = false, defaultValue = "0") int pageNo) {
        return new ResponseEntity(videoCommentService.getVideoComments(vidNo, pageNo), HttpStatus.OK);
    }
}
