package com.golearn.controller;

import com.golearn.model.Video;
import com.golearn.model.VideoSpace;
import com.golearn.service.VideoSpaceService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api("VideoSpace")
@Slf4j
public class VideoSpaceController {
    @Autowired
    private VideoSpaceService videoSpaceService;

    @GetMapping("/save")
    public ResponseEntity<Video> getVideoInSpace(@RequestHeader("X-USERNAME") int mbrNo) {
        return new ResponseEntity(videoSpaceService.getVideosInSpace(mbrNo), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity saveVideoInSpace(@RequestBody Map<String,Integer> map, @RequestHeader("X-USERNAME") int mbrNo) {
        videoSpaceService.saveVideoInSpace(map.get("vid_no"), mbrNo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/save/{vid_no}")
    public ResponseEntity removeVideoInSpace(@PathVariable("vid_no") int vidNo, @RequestHeader("X-USERNAME") int mbrNo) {
        videoSpaceService.removeVideoInSpace(vidNo, mbrNo);
        return new ResponseEntity(HttpStatus.OK);
    }
}
//