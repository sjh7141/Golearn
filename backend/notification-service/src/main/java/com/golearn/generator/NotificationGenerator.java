package com.golearn.generator;

import com.golearn.model.Notification;
import com.golearn.model.NotificationPayload;

import java.util.List;

public interface NotificationGenerator {
    List<Integer> generate(Notification notification);
    int getType();
}
