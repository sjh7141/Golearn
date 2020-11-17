package com.golearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name="gl_video_request")
@NoArgsConstructor
@AllArgsConstructor
public class VideoRequest {
    @Id
    private int vidReqNo;
    private int vidNo;
    private int mbrReqNo;
    private int cosNo;
    private int idxNo;
    @Column(columnDefinition = "TEXT")
    private String vidReqComment;
    @Column(columnDefinition = "TINYINT")
    private int vidReqAcceptYn;
    private int mbrAdminNo;
    @Column(columnDefinition = "TEXT")
    private String vidResComment;
    private Date regDt;
    private Date chgDt;
}

