package com.golearn.service;

import com.golearn.model.Video;
import com.golearn.model.VideoCompositekey;
import com.golearn.model.VideoLike;
import com.golearn.repository.VideoLikeRepository;
import com.golearn.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoLikeRepository videoLikeRepository;

    public Video getVideo(int vidNo) {
        if(videoRepository.existsById(vidNo)){

            videoRepository.addViewCount(vidNo);
            return videoRepository.findById(vidNo).get();
        }
        return null;
    }

    public int hideVideo(int vidNo, int mbrNo){

        return videoRepository.hideVideo(vidNo, mbrNo);
    }

    public void saveVideo(int mbrNo){

    }

    public Map isLikeVideo(int vidNo, int mbrNo) {
        Map<String, Boolean> result = new HashMap();
        result.put("isLike", videoLikeRepository.existsById(new VideoCompositekey(vidNo, mbrNo)));
        return result;
    }

    public void likeVideo(int vidNo, int mbrNo) {
        VideoCompositekey videoCompositekey = new VideoCompositekey(vidNo, mbrNo);
        if (!videoLikeRepository.existsById(videoCompositekey)) {
            VideoLike videoLike = new VideoLike(videoCompositekey);
            videoLikeRepository.save(videoLike);
        }
    }

    public void unlikeVideo(int vidNo, int mbrNo) {
        VideoCompositekey videoCompositekey = new VideoCompositekey(vidNo, mbrNo);
        if (videoLikeRepository.existsById(videoCompositekey)) {
            videoLikeRepository.deleteById(videoCompositekey);
        }
    }


}
//
