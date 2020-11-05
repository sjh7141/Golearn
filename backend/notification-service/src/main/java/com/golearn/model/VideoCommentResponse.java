package com.golearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
public class VideoCommentResponse {
    private int vidCmtNo;
    private int vidNo;
    private int mbrNo;
    private int vidCmtPno;
    private String vidComment;
    private Date regDt;
    private Date chgDt;
}
