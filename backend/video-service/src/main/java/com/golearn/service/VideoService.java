package com.golearn.service;

import com.golearn.model.Video;
import com.golearn.model.VideoLike;
import com.golearn.repository.VideoLikeRepository;
import com.golearn.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoLikeRepository videoLikeRepository;

    public Video getVideo(int vidNo) {
        return videoRepository.getOne(vidNo);
    }

    public void hideVideo(int vidNo, int mbrNo) {
        videoRepository.hideVideo(vidNo, mbrNo);
    }

    public void likeVideo(int vidNo, int mbrNo) {
        if(!videoLikeRepository.existsVideoLikeByVidNoAndMbrNo(vidNo, mbrNo)){
            VideoLike videoLike = new VideoLike(vidNo, mbrNo);
            videoLikeRepository.save(videoLike);
        }
    }

    public void unlikeVideo(int vidNo, int mbrNo) {
        if(videoLikeRepository.existsVideoLikeByVidNoAndMbrNo(vidNo, mbrNo)){
            videoLikeRepository.deleteByVidNoAndMbrNo(vidNo, mbrNo);
        }
    }
}
