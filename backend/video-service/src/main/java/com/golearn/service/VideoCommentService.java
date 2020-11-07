package com.golearn.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.golearn.exception.UnAuthorizationException;
import com.golearn.model.Member;
import com.golearn.model.VideoComment;
import com.golearn.model.VideoCommentPayload;
import com.golearn.repository.MemberRepository;
import com.golearn.repository.VideoCommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class VideoCommentService {

    private final VideoCommentRepository videoCommentRepository;
    private final MemberRepository memberRepository;

    VideoCommentService(VideoCommentRepository videoCommentRepository, MemberRepository memberRepository) {
        this.videoCommentRepository = videoCommentRepository;
        this.memberRepository = memberRepository;
    }

    public List<VideoComment> getVideoComments(int vidNo, int pageNo) {
        PageRequest pageRequest = PageRequest.of(pageNo, 20, Sort.by("reg_dt").descending());
        List<VideoCommentPayload> list = videoCommentRepository.findAllByVidNo(vidNo, pageRequest);
        List<VideoComment> comments = new LinkedList<>();

        for (VideoCommentPayload videoCommentPayload : list) {
            VideoComment videoComment = new VideoComment();
            videoComment.setVidCmtNo(videoCommentPayload.getVidCmtNo());
            videoComment.setVidNo(videoCommentPayload.getVidNo());
            videoComment.setMbrNo(videoCommentPayload.getMbrNo());
            videoComment.setVidComment(videoCommentPayload.getVidComment());
            videoComment.setNumOfReply(videoCommentPayload.getNumOfReply());
            videoComment.setRegDt(videoCommentPayload.getRegDt());
            videoComment.setChgDt(videoCommentPayload.getChgDt());
            videoComment.setMember(memberRepository.findById(videoCommentPayload.getMbrNo()).get());
            comments.add(videoComment);
        }
        return comments;
    }

    public VideoComment saveVideoComment(VideoComment videoComment) {
        return videoCommentRepository.save(videoComment);
    }

    public VideoComment updateVideoComment(VideoComment videoComment, int mbrNo) {
        VideoComment comment = videoCommentRepository.findById(videoComment.getVidCmtNo()).get();
        if (comment.getMbrNo() == mbrNo) {
            comment.setVidComment(videoComment.getVidComment());
            return videoCommentRepository.save(comment);
        } else {
            throw new UnAuthorizationException();
        }

    }

    public void removeVideoComment(int vidCmtNo, int mbrNo) {
        if (videoCommentRepository.deleteByVidCmtNoAndMbrNo(vidCmtNo, mbrNo) == 0) {
            throw new UnAuthorizationException();
        }
    }

    public List<VideoComment> getVideoReplies(int vidCmtNo) {
        List<VideoComment> comments = videoCommentRepository.findAllByVidCmtPnoOrderByRegDtAsc(vidCmtNo);
        for(VideoComment videoComment: comments){
            videoComment.setMember(memberRepository.findById(videoComment.getMbrNo()).get());
        }
        return comments;
    }

    public VideoComment getVideoComment(int vidCmtNo) {
        return videoCommentRepository.findById(vidCmtNo).get();
    }
}
