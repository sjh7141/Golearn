package com.golearn.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "gl_video")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "vidNo")
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

//    @JoinColumn(name="gl_video_tag",nullable = false)
    @OneToMany(mappedBy="tagCompositeKey.video",fetch = FetchType.LAZY)
    private List<VideoTag> videoTags;
}
