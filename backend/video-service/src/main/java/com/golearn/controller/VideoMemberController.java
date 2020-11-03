package com.golearn.controller;

import com.golearn.model.Video;
import com.golearn.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@Api("VideoMember")
public class VideoMemberController {
    private final VideoService videoService;

    VideoMemberController(VideoService videoService) {
        this.videoService = videoService;
    }
    @ApiOperation(value = "유저별 업로드 동영상 조회")
    @GetMapping("/member/{mbr_no}")
    public ResponseEntity<List<Video>> getMemberVideos(@PathVariable("mbr_no") int mbrNo){
        return new ResponseEntity(videoService.getVideos(mbrNo), HttpStatus.OK);
    }
}
