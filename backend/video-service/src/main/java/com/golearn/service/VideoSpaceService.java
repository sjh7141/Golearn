package com.golearn.service;

import com.golearn.model.Video;
import com.golearn.model.VideoCompositekey;
import com.golearn.model.VideoSpace;
import com.golearn.repository.VideoRepository;
import com.golearn.repository.VideoSpaceRepository;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VideoSpaceService {
    @Autowired
    private VideoSpaceRepository videoSpaceRepository;
    @Autowired
    private VideoRepository videoRepository;

    public List<Video> getVideosInSpace(int mbrNo) {
        return videoRepository.findAllByMbrNo(mbrNo);
    }
    public void saveVideoInSpace(int vidNo, int mbrNo){
        VideoCompositekey videoCompositekey = new VideoCompositekey(vidNo, mbrNo);
        videoSpaceRepository.save(new VideoSpace(videoCompositekey));
    }
    public void removeVideoInSpace(int vidNo, int mbrNo){
        VideoCompositekey videoCompositekey = new VideoCompositekey(vidNo, mbrNo);
        videoSpaceRepository.deleteById(videoCompositekey);
    }
}
//