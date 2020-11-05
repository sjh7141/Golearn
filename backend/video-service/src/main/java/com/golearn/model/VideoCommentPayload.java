package com.golearn.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

public interface VideoCommentPayload {

    int getVidCmtNo();

    int getVidNo();

    int getMbrNo();

    int getVidCmtPno();

    String getVidComment();

    Date getRegDt();

    Date getChgDt();

    int getNumOfReply();

}
