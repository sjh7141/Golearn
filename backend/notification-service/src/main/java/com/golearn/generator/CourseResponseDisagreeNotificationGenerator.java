package com.golearn.generator;

import com.golearn.model.Notification;
import com.golearn.model.NotificationPayload;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class CourseResponseDisagreeNotificationGenerator implements NotificationGenerator{
    private int type=7;
    @Override
    public List<Integer> generate(Notification notification) {
        return null;
    }
}
