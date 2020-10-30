package com.golearn.generator;

import com.golearn.model.Notification;
import com.golearn.model.NotificationPayload;

public interface NotificationGenerator {
    Notification generate(NotificationPayload notificationPayload);
}
