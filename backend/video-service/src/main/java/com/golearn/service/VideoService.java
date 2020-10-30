package com.golearn.service;

import com.golearn.exception.UnAuthorizationException;
import com.golearn.model.Video;
import com.golearn.model.VideoCompositeKey;
import com.golearn.model.VideoLike;
import com.golearn.repository.VideoLikeRepository;
import com.golearn.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class VideoService {
    private final VideoRepository videoRepository;

    private final VideoLikeRepository videoLikeRepository;

    VideoService(VideoRepository videoRepository, VideoLikeRepository videoLikeRepository) {
        this.videoRepository = videoRepository;
        this.videoLikeRepository = videoLikeRepository;
    }

    public Video getVideo(int vidNo) {
        if (videoRepository.existsById(vidNo)) {
            videoRepository.addViewCount(vidNo);
            return videoRepository.findById(vidNo).get();
        }
        return null;
    }

    public void hideVideo(int vidNo, int mbrNo) {
        if (videoRepository.hideVideo(vidNo, mbrNo) == 0) {
            throw new UnAuthorizationException();
        }
    }

    public void saveVideo(int mbrNo) {

    }

    public Map isLikeVideo(int vidNo, int mbrNo) {
        Map<String, Boolean> result = new HashMap();
        result.put("isLike", videoLikeRepository.existsById(new VideoCompositeKey(vidNo, mbrNo)));
        return result;
    }

    public void likeVideo(int vidNo, int mbrNo) {
        VideoCompositeKey videoCompositekey = new VideoCompositeKey(vidNo, mbrNo);
        if (!videoLikeRepository.existsById(videoCompositekey)) {
            VideoLike videoLike = new VideoLike(videoCompositekey);
            videoLikeRepository.save(videoLike);
        }
    }

    public void unlikeVideo(int vidNo, int mbrNo) {
        VideoCompositeKey videoCompositekey = new VideoCompositeKey(vidNo, mbrNo);
        if (videoLikeRepository.existsById(videoCompositekey)) {
            videoLikeRepository.deleteById(videoCompositekey);
        }
    }

    public List<Video> getLikeVideo(int mbrNo) {
        return videoRepository.findAllByMbrNoAndLikeVideo(mbrNo);
    }

    public List<Video> getVideos(int mbrNo) {
        return videoRepository.findAllByMbrNo(mbrNo);
    }
}
//
