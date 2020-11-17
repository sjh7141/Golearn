package com.golearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Entity(name="gl_notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    private int notiNo;
    private int mbrNo;
    @Column(columnDefinition = "TINYINT(1)")
    private int notiType;
    private String notiPath;
    @Column(columnDefinition = "TEXT")
    private String notiMsg;
    @CreationTimestamp
    private Date regDt;
    @Column(columnDefinition = "TINYINT(1)")
    private int notiRead;
    private int notiSender;
    @Transient
    private int dest;
    @Transient
    private String profile;
    @Transient
    private String senderNickname;

}
