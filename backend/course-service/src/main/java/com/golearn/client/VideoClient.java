package com.golearn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.golearn.dto.VideoDto;

@FeignClient(name = "video-service", url="k3a402.p.ssafy.io:8801/video")
public interface VideoClient {
	
	@GetMapping("/{vid_no}")
	public ResponseEntity<VideoDto> getVideo(@PathVariable("vid_no") int vidNo);
}
