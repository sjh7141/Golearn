package com.golearn.service;

import com.golearn.client.AccountRestClient;
import com.golearn.factory.NotificationGeneratorFactory;
import com.golearn.generator.NotificationGenerator;
import com.golearn.model.CountPayload;
import com.golearn.model.MemberResponse;
import com.golearn.model.Notification;
import com.golearn.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationGeneratorFactory notificationGeneratorFactory;
    private final AccountRestClient accountRestClient;
    NotificationService(NotificationRepository notificationRepository, NotificationGeneratorFactory notificationGeneratorFactory, AccountRestClient accountRestClient) {
        this.notificationRepository = notificationRepository;
        this.notificationGeneratorFactory = notificationGeneratorFactory;
        this.accountRestClient = accountRestClient;
    }

    public void sendNotification(Notification notification) {
        NotificationGenerator notificationGenerator = notificationGeneratorFactory.getGenerator(notification.getNotiType());
        log.info("제네레이터 입니다. : " + notificationGenerator);
        List<Integer> list = notificationGenerator.generate(notification);
        for (int no : list) {
            Notification tempNoti = new Notification(no,notification.getNotiType(),notification.getNotiPath(),notification.getNotiMsg(),notification.getNotiSender());
            notificationRepository.save(tempNoti);
        }
    }

    @Transactional
    public List<Notification> getNotifications(int mbrNo) {
        List<Notification> notificationList = notificationRepository.findAllByMbrNoOrderByRegDtDesc(mbrNo);
        for(Notification notification : notificationList){
            MemberResponse memberResponse = accountRestClient.getMember(notification.getNotiSender());
            notification.setProfile(memberResponse.getProfile());
            notification.setSenderNickname(memberResponse.getNickname());
        }
        notificationRepository.readNotificationByMbrNo(mbrNo);
        return notificationList;
    }

    public CountPayload getNotificationCount(int mbrNo) {
        return notificationRepository.countAllByMbrNo(mbrNo);
    }

    public void removeNotification(int mbrNo, int notiNo) {
        notificationRepository.deleteByMbrNoAndNotiNo(mbrNo, notiNo);
    }

    public void removeNotifications(int mbrNo) {
        notificationRepository.deleteAllByMbrNo(mbrNo);
    }
}
