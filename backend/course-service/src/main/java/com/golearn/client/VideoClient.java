package com.golearn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.golearn.domain.VideoDto;

@FeignClient(name = "video-service")
public interface VideoClient {
	
	@GetMapping("/{vid_no}")
	VideoDto getVideo(@PathVariable("vid_no") int vidNo);
}
