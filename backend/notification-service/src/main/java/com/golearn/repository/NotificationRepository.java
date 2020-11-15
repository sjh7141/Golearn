package com.golearn.repository;

import com.golearn.model.CountPayload;
import com.golearn.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByMbrNoOrderByRegDtDesc(int mbrNo);

    //    int countAllByMbrNoAndNotiReadIsFalse(int mbrNo);
    @Transactional
    void deleteAllByMbrNo(int mbrNo);
    @Transactional
    void deleteByMbrNoAndNotiNo(int mbrNo, int notiNo);
    @Transactional
    @Modifying
    @Query(value = "update gl_notification set noti_read=1 where mbr_no=:mbrNo and noti_read=0", nativeQuery = true)
    void readNotificationByMbrNo(int mbrNo);

    @Query(value = "select count(*) as numOfNoti from gl_notification where mbr_no = :mbrNo and noti_read = 0", nativeQuery = true)
    CountPayload countAllByMbrNo(@Param("mbrNo") int mbrNo);
}
