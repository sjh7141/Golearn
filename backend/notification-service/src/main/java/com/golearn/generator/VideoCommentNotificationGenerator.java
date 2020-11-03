package com.golearn.generator;

import com.golearn.client.VideoRestClient;
import com.golearn.model.Notification;
import com.golearn.model.VideoResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Data
@Component
public class VideoCommentNotificationGenerator implements NotificationGenerator {
    private int type = 3;
    private final VideoRestClient videoRestClient;

    VideoCommentNotificationGenerator(VideoRestClient videoRestClient) {
        this.videoRestClient = videoRestClient;
    }

    @Override
    public List<Integer> generate(Notification notification) {
        VideoResponse videoResponse = videoRestClient.getVideo(notification.getDest());
        List<Integer> list = new LinkedList<>();
        list.add(videoResponse.getMbrNo());
        return list;
    }
}
