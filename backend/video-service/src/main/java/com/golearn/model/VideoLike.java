package com.golearn.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name="gl_video_like")
@Data
@NoArgsConstructor
public class VideoLike {

    @Id
    private int vidNo;
    @Id
    private int mbrNo;

    private Date regDt;

    public VideoLike(int vidNo, int mbrNo){
        this.vidNo = vidNo;
        this.mbrNo = mbrNo;
    }
}
