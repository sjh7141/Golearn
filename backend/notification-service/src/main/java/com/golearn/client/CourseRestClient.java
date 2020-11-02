package com.golearn.client;

import com.golearn.model.CourseManagerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "course-api", url = "${feign.course-api.url}")
public interface CourseRestClient{
    @GetMapping("/manager/{cos_no}")
    List<CourseManagerResponse> getCourse(@PathVariable("cos_no") int cosNo);

}
