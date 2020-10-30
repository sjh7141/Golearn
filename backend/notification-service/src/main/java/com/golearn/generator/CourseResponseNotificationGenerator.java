package com.golearn.generator;

import com.golearn.model.Notification;
import com.golearn.model.NotificationPayload;

public class CourseResponseNotificationGenerator implements NotificationGenerator{
    private static CourseResponseNotificationGenerator courseResponseNotificationGenerator;
    private CourseResponseNotificationGenerator(){

    }
    public static CourseResponseNotificationGenerator getObject(){
        return courseResponseNotificationGenerator;
    }
    @Override
    public Notification generate(NotificationPayload notificationPayload) {
        return null;
    }
}
