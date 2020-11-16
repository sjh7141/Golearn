package com.golearn.service;

import com.golearn.model.Video;
import com.golearn.model.VideoCompositeKey;
import com.golearn.model.VideoSpace;
import com.golearn.repository.VideoRepository;
import com.golearn.repository.VideoSpaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VideoSpaceService {
    private final VideoSpaceRepository videoSpaceRepository;

    private final VideoRepository videoRepository;

    VideoSpaceService(VideoSpaceRepository videoSpaceRepository, VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
        this.videoSpaceRepository = videoSpaceRepository;
    }

    public List<Video> getVideosInSpace(int mbrNo) {
        return videoRepository.findAllSpaceVideo(mbrNo);
    }

    public void saveVideoInSpace(int vidNo, int mbrNo) {
        VideoCompositeKey videoCompositekey = new VideoCompositeKey(vidNo, mbrNo);
        videoSpaceRepository.save(new VideoSpace(videoCompositekey));
    }

    public void removeVideoInSpace(int vidNo, int mbrNo) {
        VideoCompositeKey videoCompositekey = new VideoCompositeKey(vidNo, mbrNo);
        videoSpaceRepository.deleteById(videoCompositekey);
    }
    
    public int checkMyVideo(long mbrNo, long vidNo) {
    	return videoSpaceRepository.checkMyVideo(mbrNo, vidNo);
    }
}
