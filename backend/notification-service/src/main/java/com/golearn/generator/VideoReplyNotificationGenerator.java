package com.golearn.generator;

import com.golearn.client.VideoRestClient;
import com.golearn.model.Notification;
import com.golearn.model.VideoCommentResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Data
@Component
public class VideoReplyNotificationGenerator implements NotificationGenerator{
    private int type=4;
    private VideoRestClient videoRestClient;
    VideoReplyNotificationGenerator(VideoRestClient videoRestClient){
        this.videoRestClient = videoRestClient;
    }
    @Override
    public List<Integer> generate(Notification notification) {
        VideoCommentResponse videoCommentResponse = videoRestClient.getVideoComment(notification.getDest());
//        notification.setMbrNo(videoCommentResponse.getMbrNo());
        List<Integer> list = new LinkedList<>();
        list.add(videoCommentResponse.getMbrNo());
        return list;
    }
}
