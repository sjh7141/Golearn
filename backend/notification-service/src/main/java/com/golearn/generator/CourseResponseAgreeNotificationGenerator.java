package com.golearn.generator;

import com.golearn.model.Notification;
import com.golearn.model.VideoRequest;
import com.golearn.repository.VideoRequestRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Data
@Component
public class CourseResponseAgreeNotificationGenerator implements NotificationGenerator {
    private int type = 6;
    private final VideoRequestRepository videoRequestRepository;
    CourseResponseAgreeNotificationGenerator(VideoRequestRepository videoRequestRepository) {
        this.videoRequestRepository = videoRequestRepository;
    }

    @Override
    public List<Integer> generate(Notification notification) {
        VideoRequest videoRequest = videoRequestRepository.findById(notification.getDest()).get();
        List<Integer> list = new LinkedList<>();
        list.add(videoRequest.getMbrReqNo());
        return list;
    }
}
