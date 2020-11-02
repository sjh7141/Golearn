package com.golearn.generator;

import com.golearn.model.Notification;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class CourseResponseAgreeNotificationGenerator implements NotificationGenerator{
    private int type=6;
    @Override
    public List<Integer> generate(Notification notification) {
        return null;
    }
}
