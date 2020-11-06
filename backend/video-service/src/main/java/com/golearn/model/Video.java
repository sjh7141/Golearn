package com.golearn.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "gl_video")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Data
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "vidNo")
public class Video {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    @CreationTimestamp
    private Date regDt;

    private boolean vidHide;

    private String vidThumbnail;

    private int vidLength;

    @Transient
    private List<Tag> tags;
    @Transient
    private int vidLikes;
}
