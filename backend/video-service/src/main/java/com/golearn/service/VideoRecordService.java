package com.golearn.service;


import com.golearn.model.Video;
import com.golearn.model.VideoCompositeKey;
import com.golearn.model.VideoRecord;
import com.golearn.model.VideoRecordPayload;
import com.golearn.repository.VideoRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class VideoRecordService {

    private final VideoRecordRepository videoRecordRepository;

    VideoRecordService(VideoRecordRepository videoRecordRepository) {
        this.videoRecordRepository = videoRecordRepository;
    }

    public List<VideoRecordPayload> getRecords(int mbrNo, List<Integer> vidNos){
        log.info(""+mbrNo+vidNos.toString());
        return videoRecordRepository.findAllByMbrNo(mbrNo, vidNos);
//        return videoRecordRepository.findAll();
    }


    public VideoRecord getRecord(int mbrNo, int vidNo) {
        VideoCompositeKey videoCompositekey = new VideoCompositeKey(vidNo, mbrNo);
        return videoRecordRepository.findById(videoCompositekey).get();
    }

    public void saveRecord(int mbrNo, int vidNo, int vid_rec_time) {
        VideoCompositeKey videoCompositekey = new VideoCompositeKey(vidNo, mbrNo);
        VideoRecord videoRecord = new VideoRecord(videoCompositekey, vid_rec_time);
        videoRecordRepository.save(videoRecord);
    }
}
