package com.golearn.client;

import com.golearn.model.CourseManagerResponse;
import com.golearn.model.VideoCommentResponse;
import com.golearn.model.VideoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "video-api", url = "${feign.video-api.url}")
public interface VideoRestClient {
    @GetMapping("/{vid_no}")
    VideoResponse getVideo(@PathVariable("vid_no") int vidNo);
    @GetMapping("/comment/details/{vid_cmt_no}")
    VideoCommentResponse getVideoComment(@PathVariable("vid_cmt_no") int vidCmtNo);
}
