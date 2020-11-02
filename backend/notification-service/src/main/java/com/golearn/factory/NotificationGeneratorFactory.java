package com.golearn.factory;

import com.golearn.generator.BoardCommentNotificationGenerator;
import com.golearn.generator.CourseResponseDisagreeNotificationGenerator;
import com.golearn.generator.CourseRequestNotificationGenerator;
import com.golearn.generator.NotificationGenerator;
import com.golearn.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class NotificationGeneratorFactory {
    private final Map<Integer,NotificationGenerator> notificationGenerator = new HashMap<>();
    NotificationGeneratorFactory(List<NotificationGenerator> notificationGeneratorList){
        if(CollectionUtils.isEmpty(notificationGeneratorList)) {
            throw new IllegalArgumentException("존재하는 notificationGeneratorList 없음");
        }
        for(NotificationGenerator notificationGenerator : notificationGeneratorList){

            this.notificationGenerator.put(notificationGenerator.getType(),notificationGenerator);
        }
    }
    public NotificationGenerator getGenerator(int type) {
        return notificationGenerator.get(type);
    }
}
