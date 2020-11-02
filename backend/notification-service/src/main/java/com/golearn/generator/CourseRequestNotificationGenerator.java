package com.golearn.generator;

import com.golearn.client.CourseRestClient;
import com.golearn.model.CourseManagerResponse;
import com.golearn.model.Notification;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Slf4j
public class CourseRequestNotificationGenerator implements NotificationGenerator{
    private int type=5;
    private final CourseRestClient courseRestClient;
    CourseRequestNotificationGenerator(CourseRestClient courseRestClient){
        this.courseRestClient = courseRestClient;
    }
    @Override
    public List<Integer> generate(Notification notification) {
        List<CourseManagerResponse> courseManagerList = courseRestClient.getCourse(notification.getDest());
        for(CourseManagerResponse courseManagerResponse : courseManagerList){

        }
        return null;
    }
}
