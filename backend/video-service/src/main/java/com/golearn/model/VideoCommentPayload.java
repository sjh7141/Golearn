package com.golearn.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

public interface VideoCommentPayload {

    Long getVidCmtNo();

    Long getVidNo();

    Long getMbrNo();

    Long getVidCmtPno();

    String getVidComment();

    Date getRegDt();

    Date getChgDt();

    Long getNumOfReply();
}
