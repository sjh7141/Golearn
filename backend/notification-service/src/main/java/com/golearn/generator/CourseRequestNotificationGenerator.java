package com.golearn.generator;

import com.golearn.model.Notification;
import com.golearn.model.NotificationPayload;

public class CourseRequestNotificationGenerator implements NotificationGenerator{
    private static CourseRequestNotificationGenerator courseRequestNotificationGenerator;
    private CourseRequestNotificationGenerator(){

    }
    public static CourseRequestNotificationGenerator getObject(){
        return courseRequestNotificationGenerator;
    }
    @Override
    public Notification generate(NotificationPayload notificationPayload) {
        return null;
    }
}
