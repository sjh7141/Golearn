package com.golearn.client;

import com.golearn.model.CourseManagerResponse;
import com.golearn.model.VideoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "course-api", url = "${feign.course-api.url}")
public interface CourseRestClient{
    @GetMapping("/manager/{cos_no}")
    List<CourseManagerResponse> getCourseManager(@PathVariable("cos_no") int cosNo);
    @GetMapping("/video/{vid_req_no}")
    VideoRequest getVideoRequest(@PathVariable("vid_req_no") int vidReqNo);

}
