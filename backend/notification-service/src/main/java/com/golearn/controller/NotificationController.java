package com.golearn.controller;


import com.golearn.model.Notification;
import com.golearn.model.NotificationPayload;
import com.golearn.service.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api("Notification")
public class NotificationController {
    private final NotificationService notificationService;

    NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @ApiOperation("알림 생성")
    @PostMapping
    public ResponseEntity sendNotification(@RequestBody Notification notification,@RequestHeader("X-USERNO") int mbrNo) {
        notification.setNotiSender(mbrNo);
        notificationService.sendNotification(notification);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation("알림 조회")
    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications(@RequestHeader("X-USERNO") int mbrNo) {
        return new ResponseEntity(notificationService.getNotifications(mbrNo), HttpStatus.OK);
    }
    @ApiOperation("알림 갯수 조회")
    @GetMapping("/count")
    public ResponseEntity<Integer> getNotificationsCount(@RequestHeader("X-USERNO") int mbrNo) {
        return new ResponseEntity(notificationService.getNotificationCount(mbrNo), HttpStatus.OK);
    }
    @ApiOperation("알림 전체 삭제")
    @DeleteMapping
    public ResponseEntity removeNotifications(@RequestHeader("X-USERNO") int mbrNo) {
        notificationService.removeNotifications(mbrNo);
        return new ResponseEntity(HttpStatus.OK);
    }
    @ApiOperation("알림 삭제")
    @DeleteMapping("/{noti_no}")
    public ResponseEntity removeNotification(@RequestHeader("X-USERNO") int mbrNo, @PathVariable("noti_no") int notiNo) {
        notificationService.removeNotification(mbrNo, notiNo);
        return new ResponseEntity(HttpStatus.OK);
    }
}
