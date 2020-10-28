package com.golearn.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
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

    private VideoCommentRepository videoCommentRepository;

    VideoCommentService(VideoCommentRepository videoCommentRepository){
        this.videoCommentRepository = videoCommentRepository;
    }

    public List<VideoCommentPayload> getVideoComments(int vidNo, int pageNo) {
        PageRequest pageRequest = PageRequest.of(pageNo,20, Sort.by("reg_dt").descending());
        List<VideoCommentPayload> l = videoCommentRepository.findAllByVidNo(vidNo, pageRequest);
//        log.info("result = " +l.get(0)[0].toString());
        return l;
    }
}
