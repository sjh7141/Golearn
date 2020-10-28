package com.golearn.controller;

import com.golearn.model.VideoRecord;
import com.golearn.service.VideoRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class VideoRecordController {

    private VideoRecordService videoRecordService;

    VideoRecordController(VideoRecordService videoRecordService){
        this.videoRecordService = videoRecordService;
    }

    @GetMapping("/record/{vid_no}")
    public ResponseEntity<VideoRecord> getRecord(@RequestHeader("X-USERNO") int mbrNo, @PathVariable("vid_no") int vidNo){
        return new ResponseEntity(videoRecordService.getRecord(mbrNo, vidNo), HttpStatus.OK);
    }

    @PostMapping("/record")
    public ResponseEntity saveRecord(@RequestHeader("X-USERNO") int mbrNo, Map<String,Integer> map){
        videoRecordService.saveRecord(mbrNo, map.get("vid_no"),map.get("vid_rec_time"));
        return new ResponseEntity(HttpStatus.OK);
    }
}
