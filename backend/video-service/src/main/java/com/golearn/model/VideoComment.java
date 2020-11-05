package com.golearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name="gl_video_comment")
@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
public class VideoComment {
    @Id
    private int vidCmtNo;

    private int vidNo;

    private int mbrNo;

    private int vidCmtPno;
    @Column(columnDefinition = "TEXT")
    private String vidComment;
    @CreationTimestamp
    private Date regDt;
    @UpdateTimestamp
    private Date chgDt;

    @Transient
    private Member member;
    @Transient
    private int numOfReply;

}
