package com.golearn.generator;

import com.golearn.model.Notification;
import com.golearn.model.NotificationPayload;

public class BoardNotificationGenerator implements NotificationGenerator{
    private static BoardNotificationGenerator boardNotificationGenerator = new BoardNotificationGenerator();
    private BoardNotificationGenerator(){

    }
    public static BoardNotificationGenerator getObject(){
        return boardNotificationGenerator;
    }
    @Override
    public Notification generate(NotificationPayload notificationPayload) {
        return null;
    }
}
