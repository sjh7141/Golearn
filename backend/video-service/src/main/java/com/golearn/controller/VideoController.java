package com.golearn.controller;

import com.golearn.model.Video;
import com.golearn.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@Api("Video")
public class VideoController {

    private final VideoService videoService;

    VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @ApiOperation(value = "내가 올린 영상 조회")
    @GetMapping
    public ResponseEntity<List<Video>> getVideos(@ApiIgnore @RequestHeader("X-USERNO") int mbrNo) {
        return new ResponseEntity(videoService.getVideos(mbrNo), HttpStatus.OK);
    }

    @ApiOperation(value = "영상 조회")
    @GetMapping("{vid_no}")
    public ResponseEntity<Video> getVideo(@PathVariable("vid_no") int vidNo) {
        return new ResponseEntity(videoService.getVideo(vidNo), HttpStatus.OK);
    }

    //TODO
    @ApiOperation(value = "영상 올리기")
    @PostMapping
    public ResponseEntity saveVideo(@ApiIgnore @RequestHeader("X-USERNO") int mbrNo, @RequestBody Video video) {
    	video.setMbrNo(mbrNo);
    	video = videoService.saveVideo(video);
    	videoService.saveTag(video.getTags(), video.getVidNo());
//        return new ResponseEntity(HttpStatus.CREATED);
    	return ResponseEntity.ok(video.getVidNo());
    }

    @ApiOperation(value = "영상 숨기기")
    @DeleteMapping
    public ResponseEntity hideVideo(@RequestBody Map<String, Object> map, @ApiIgnore @RequestHeader("X-USERNO") int mbrNo) {
        videoService.hideVideo((List<Integer>) map.get("hide_list"), mbrNo);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "좋아요한 영상")
    @GetMapping("/like")
    public ResponseEntity<List<Video>> getLikeVideos(@ApiIgnore @RequestHeader("X-USERNO") int mbrNo) {
        return new ResponseEntity(videoService.getLikeVideo(mbrNo), HttpStatus.OK);
    }

    @ApiOperation(value = "영상 좋아요 여부")
    @GetMapping("/like/{vid_no}")
    public ResponseEntity isLikeVideo(@PathVariable("vid_no") int vidNo, @ApiIgnore @RequestHeader("X-USERNO") int mbrNo) {
        return new ResponseEntity(videoService.isLikeVideo(vidNo, mbrNo), HttpStatus.OK);
    }

    @ApiOperation(value = "영상 좋아요")
    @PostMapping("/like/{vid_no}")
    public ResponseEntity likeVideo(@PathVariable("vid_no") int vidNo, @ApiIgnore @RequestHeader("X-USERNO") int mbrNo) {
        videoService.likeVideo(vidNo, mbrNo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "영상 좋아요 취소")
    @DeleteMapping("/like/{vid_no}")
    public ResponseEntity unlikeVideo(@PathVariable("vid_no") int vidNo, @ApiIgnore @RequestHeader("X-USERNO") int mbrNo) {
        videoService.unlikeVideo(vidNo, mbrNo);
        return new ResponseEntity(HttpStatus.OK);
    }
}