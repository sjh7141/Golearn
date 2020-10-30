package com.golearn.factory;

import com.golearn.generator.BoardNotificationGenerator;
import com.golearn.generator.CourseResponseNotificationGenerator;
import com.golearn.generator.CourseRequestNotificationGenerator;
import com.golearn.model.Notification;
import com.golearn.model.NotificationPayload;

public class NotificationGeneratorFactory {
    public Notification generateNotification(NotificationPayload notificationPayload) {
        Notification notification = null;
        switch (notificationPayload.getType()) {
            case 1:
                notification = BoardNotificationGenerator.getObject().generate(notificationPayload);
                break;
            case 2:
                notification = CourseRequestNotificationGenerator.getObject().generate(notificationPayload);
                break;
            default:
                notification = CourseResponseNotificationGenerator.getObject().generate(notificationPayload);
                break;

        }
        return notification;
    }
}
