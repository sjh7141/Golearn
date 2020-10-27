package com.golearn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name="gl_video_like")
@Data
@DynamicInsert
@AllArgsConstructor
public class VideoLike {
    @EmbeddedId
    private VideoCompositekey videoCompositekey;

    private Date regDt;

    public VideoLike(VideoCompositekey videoCompositekey){
        this.videoCompositekey = videoCompositekey;
    }
    public VideoLike(){

    }
}
