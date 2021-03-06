package com.golearn.controller;

import com.golearn.model.VideoRecord;
import com.golearn.model.VideoRecordPayload;
import com.golearn.service.VideoRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@RestController
@Api("VideoRecord")
@Slf4j
public class VideoRecordController {

    private final VideoRecordService videoRecordService;

    VideoRecordController(VideoRecordService videoRecordService) {
        this.videoRecordService = videoRecordService;
    }

    @ApiOperation(value = "영상  목록 시청 기록 조회")
    @GetMapping("/record")
    public ResponseEntity<List<VideoRecordPayload>> getRecords(@ApiIgnore @RequestHeader("X-USERNO") int mbrNo, @RequestParam("vid_nos") List<Integer> vidNos) {
        return new ResponseEntity(videoRecordService.getRecords(mbrNo,vidNos), HttpStatus.OK);
    }

    @ApiOperation(value = "영상 시청 기록 조회")
    @GetMapping("/record/{vid_no}")
    public ResponseEntity<VideoRecord> getRecord(@ApiIgnore @RequestHeader("X-USERNO") int mbrNo, @PathVariable("vid_no") int vidNo) {
        return new ResponseEntity(videoRecordService.getRecord(mbrNo, vidNo), HttpStatus.OK);
    }

    @ApiOperation(value = "영상 시청 기록 저장")
    @PostMapping("/record")
    public ResponseEntity saveRecord(@ApiIgnore @RequestHeader("X-USERNO") int mbrNo, Map<String, Integer> map) {
        videoRecordService.saveRecord(mbrNo, map.get("vid_no"), map.get("vid_rec_time"));
        return new ResponseEntity(HttpStatus.OK);
    }
}
