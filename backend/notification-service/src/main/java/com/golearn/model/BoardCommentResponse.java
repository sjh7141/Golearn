package com.golearn.model;

import lombok.Data;

import java.util.Date;
@Data
public class BoardCommentResponse {

    private int cmtNo;
    private int brdNo;
    private int parentNo;
    private int mbrNo;
    private String comment;
    private Date regDate;
    private Date updateDate;

}
