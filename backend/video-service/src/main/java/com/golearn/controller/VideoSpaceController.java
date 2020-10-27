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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
@Api("VideoSpace")
@Slf4j
public class VideoSpaceController {
    @Autowired
    private VideoSpaceService videoSpaceService;

    @GetMapping("/save")
    public ResponseEntity<VideoSpace> getVideo(@RequestHeader("X-USERNAME") int mbrNo) {
        return new ResponseEntity(videoSpaceService.getVideos(mbrNo), HttpStatus.OK);
    }

}
