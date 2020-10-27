package com.golearn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "gl_video")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    private int vidNo;

    private int mbrNo;

    private int vidPno;

    private String vidTitle;

    private String vidContent;

    private String vidUrl;

    private int vidView;

    private Date regDt;

    private boolean vidHide;

    private String vidThumbnail;

    private int vidLength;

}
