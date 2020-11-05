package com.golearn.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "gl_video")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "vidNo")
public class Video {
    @Id
    private int vidNo;
//    @OneToOne
//    @JoinColumn(name="mbr_no")
//    private Member mbrNo;
//    @OneToOne
//    @JoinColumn(name = "vid_pno")
//    private Video vidPno;
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

    @Transient
    private List<Tag> tags;
    @Transient
    private int vidLikes;
    public String toString(){
        return "";
    }
}
