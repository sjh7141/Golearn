package com.golearn.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

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
    private int mbrNo;
    private int vidPno;

    private String vidTitle;
    @Column(columnDefinition = "TEXT")
    private String vidContent;

    private String vidUrl;

    private int vidView;
    @CreationTimestamp
    private Date regDt;

    private boolean vidHide;

    private String vidThumbnail;

    private int vidLength;

    @Column(columnDefinition = "TEXT")
    private String vidCode;

    private String vidCodeType;

    @Transient
    private List<Tag> tags;
    @Transient
    private int vidLikes;
}
