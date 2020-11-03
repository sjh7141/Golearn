package com.golearn.model;

import lombok.Data;

import java.util.Date;

@Data
public class VideoRequestResponse {
    private int vidReqNo;
    private int vidNo;
    private int mbrReqNo;
    private int cosNo;
    private int idxNo;
    private String vidReqComment;
    private int vidReqAcceptYn;
    private Long mbrAdminNo;
    private String vidResComment;
    private Date regDt;
    private Date chgDt;
}

