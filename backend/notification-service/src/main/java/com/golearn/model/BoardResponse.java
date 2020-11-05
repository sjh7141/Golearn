package com.golearn.model;

import lombok.Data;

import java.util.Date;
@Data
public class BoardResponse {

    private int brdNo;
    private int mbrNo;
    private String title;
    private String content;
    private Date regDate;
    private Date updateDate;
    private int type;
    private int viewCount;

}
