package com.golearn.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "gl_video_tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "tagCompositeKey")
public class VideoTag {
    @EmbeddedId
    private TagCompositeKey tagCompositeKey;

//    @MapsId("vidNo")
//    @ManyToOne
//    @JoinColumn(name="vid_no",updatable = false, nullable = false)
//    private Video video;
//
//    @MapsId("tagNo")
//    @ManyToOne
//    @JoinColumn(name="tag_no",updatable = false, nullable = false)
//    private Tag tag;
}
