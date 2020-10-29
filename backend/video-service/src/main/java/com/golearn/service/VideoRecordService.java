package com.golearn.service;


import com.golearn.model.VideoCompositeKey;
import com.golearn.model.VideoRecord;
import com.golearn.repository.VideoRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class VideoRecordService {

    private VideoRecordRepository videoRecordRepository;

    VideoRecordService(VideoRecordRepository videoRecordRepository) {
        this.videoRecordRepository = videoRecordRepository;
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
