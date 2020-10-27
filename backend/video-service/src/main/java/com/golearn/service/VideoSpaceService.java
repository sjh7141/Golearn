package com.golearn.service;

import com.golearn.model.Video;
import com.golearn.repository.VideoSpaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VideoSpaceService {
    @Autowired
    private VideoSpaceRepository videoSpaceRepository;


    public List<Video> getVideos(int mbrNo) {
        return videoSpaceRepository.findAllByMbrNo(mbrNo);
    }
}
