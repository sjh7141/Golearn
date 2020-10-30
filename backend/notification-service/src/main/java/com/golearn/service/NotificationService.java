package com.golearn.service;

import com.golearn.factory.NotificationGeneratorFactory;
import com.golearn.model.Notification;
import com.golearn.model.NotificationPayload;
import com.golearn.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private NotificationGeneratorFactory notificationGeneratorFactory;
    NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
        this.notificationGeneratorFactory = new NotificationGeneratorFactory();
    }

    public void sendNotification(NotificationPayload notificationPayload) {
        Notification notification = notificationGeneratorFactory.generateNotification(notificationPayload);
        notificationRepository.save(notification);
    }

    @Transactional
    public List<Notification> getNotifications(int mbrNo) {
        List<Notification> notificationList = notificationRepository.findAllByMbrNo(mbrNo);
        notificationRepository.readNotificationByMbrNo(mbrNo);
        return notificationList;
    }

    public int getNotificationCount(int mbrNo) {
        return notificationRepository.countAllByMbrNoAndNotiReadIsFalse(mbrNo);
    }

    public void removeNotification(int mbrNo, int notiNo){
        notificationRepository.deleteByMbrNoAndNotiNo(mbrNo, notiNo);
    }

    public void removeNotifications(int mbrNo){
        notificationRepository.deleteAllByMbrNo(mbrNo);
    }
}
