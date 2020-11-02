package com.golearn.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.golearn.exception.UnAuthorizationException;
import com.golearn.model.VideoComment;
import com.golearn.model.VideoCommentPayload;
import com.golearn.repository.VideoCommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VideoCommentService {

    private final VideoCommentRepository videoCommentRepository;

    VideoCommentService(VideoCommentRepository videoCommentRepository){
        this.videoCommentRepository = videoCommentRepository;
    }

    public List<VideoCommentPayload> getVideoComments(int vidNo, int pageNo) {
        PageRequest pageRequest = PageRequest.of(pageNo,20, Sort.by("reg_dt").descending());
        return videoCommentRepository.findAllByVidNo(vidNo, pageRequest);
    }

    public void saveVideoComment(VideoComment videoComment) {
        videoCommentRepository.save(videoComment);
    }

    public void updateVideoComment(VideoComment videoComment, int mbrNo) {
        VideoComment comment = videoCommentRepository.findById(videoComment.getVidCmtNo()).get();
        if(comment.getMbrNo()==mbrNo){
            comment.setVidComment(videoComment.getVidComment());
            videoCommentRepository.save(comment);
        }
        else{
            throw new UnAuthorizationException();
        }

    }

    public void removeVideoComment(int vidCmtNo, int mbrNo) {
        if(videoCommentRepository.deleteByVidCmtNoAndMbrNo(vidCmtNo, mbrNo)==0){
            throw new UnAuthorizationException();
        }
    }

    public List<VideoComment> getVideoReplies(int vidCmtNo, int pageNo) {
        PageRequest pageRequest = PageRequest.of(pageNo,20, Sort.by("regDt").ascending());
        return videoCommentRepository.findAllByVidCmtNo(vidCmtNo,pageRequest);
    }

    public VideoComment getVideoComment(int vidCmtNo) {
        return videoCommentRepository.findById(vidCmtNo).get();
    }
}
