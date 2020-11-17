package com.golearn.generator;

import com.golearn.client.VideoRestClient;
import com.golearn.model.Notification;
import com.golearn.model.VideoResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import java.util.LinkedList;
import java.util.List;

@Data
@Component
@Slf4j
public class VideoCommentNotificationGenerator implements NotificationGenerator {
    private int type = 3;
    private final VideoRestClient videoRestClient;

    VideoCommentNotificationGenerator(VideoRestClient videoRestClient) {
        this.videoRestClient = videoRestClient;
    }

    @Override
    public List<Integer> generate(Notification notification) {
        log.info("===>"+notification.getDest());
        VideoResponse videoResponse = videoRestClient.getVideo(notification.getDest());
        JSONObject jsonObject = new JSONObject(videoResponse.getVideo());
        List<Integer> list = new LinkedList<>();
        try {
            log.info("=====>" + jsonObject.getInt("mbr_no"));
            list.add(jsonObject.getInt("mbr_no"));

        }
        catch (Exception e) {

        }

        return list;
    }
}
