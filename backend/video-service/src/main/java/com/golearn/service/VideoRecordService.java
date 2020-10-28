package com.golearn.service;


import com.golearn.model.VideoCompositekey;
import com.golearn.model.VideoRecord;
import com.golearn.repository.VideoRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoRecordService {

    private VideoRecordRepository videoRecordRepository;

    VideoRecordService(VideoRecordRepository videoRecordRepository) {
        this.videoRecordRepository = videoRecordRepository;
    }

    public VideoRecord getRecord(int mbrNo, int vidNo) {
        VideoCompositekey videoCompositekey = new VideoCompositekey(vidNo, mbrNo);
        return videoRecordRepository.findById(videoCompositekey).get();
    }

    public void saveRecord(int mbrNo, int vidNo, int vid_rec_time) {
        VideoCompositekey videoCompositekey = new VideoCompositekey(vidNo, mbrNo);
        VideoRecord videoRecord = new VideoRecord(videoCompositekey, vid_rec_time);
        videoRecordRepository.save(videoRecord);
    }
}
