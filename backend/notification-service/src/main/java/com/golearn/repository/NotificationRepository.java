package com.golearn.repository;

import com.golearn.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    List<Notification> findAllByMbrNo(int mbrNo);
    int countAllByMbrNoAndNotiReadIsFalse(int mbrNo);
    void deleteAllByMbrNo(int mbrNo);
    void deleteByMbrNoAndNotiNo(int mbrNo, int notiNo);
    @Query(value = "update gl_notification set noti_read=1 where mbr_no=:mbr_no and noti_read=0" , nativeQuery = true)
    void readNotificationByMbrNo(int mbrNo);
}
