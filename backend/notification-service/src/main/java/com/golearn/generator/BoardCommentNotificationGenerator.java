package com.golearn.generator;

import com.golearn.model.Notification;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class BoardCommentNotificationGenerator implements NotificationGenerator{
    private int type=1;

    @Override
    public List<Integer> generate(Notification notification) {
        return null;
    }
}
