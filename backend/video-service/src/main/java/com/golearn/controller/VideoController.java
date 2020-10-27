package com.golearn.controller;

import com.golearn.model.Video;
import com.golearn.service.VideoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
@Api("Video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @GetMapping("{vid_no}")
    public ResponseEntity<Video> getVideo(@PathVariable("vid_no") int vidNo) {
        return new ResponseEntity(videoService.getVideo(vidNo), HttpStatus.OK);
    }

    //TODO
    @PostMapping
    public ResponseEntity saveVideo() {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("{vid_no}")
    public ResponseEntity hideVideo(@PathVariable("vid_no") int vidNo, @RequestHeader("X-USERNAME") int mbrNo) {
        videoService.hideVideo(vidNo, mbrNo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/like/{vid_no}")
    public ResponseEntity isLikeVideo(@PathVariable("vid_no") int vidNo, @RequestHeader("X-USERNAME") int mbrNo){
        return new ResponseEntity(videoService.isLikeVideo(vidNo, mbrNo),HttpStatus.OK);
    }

    @PostMapping("/like/{vid_no}")
    public ResponseEntity likeVideo(@PathVariable("vid_no") int vidNo, @RequestHeader("X-USERNAME") int mbrNo) {
        videoService.likeVideo(vidNo, mbrNo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/like/{vid_no}")
    public ResponseEntity unlikeVideo(@PathVariable("vid_no") int vidNo, @RequestHeader("X-USERNAME") int mbrNo) {
        videoService.unlikeVideo(vidNo, mbrNo);
        return new ResponseEntity(HttpStatus.OK);
    }
}
