package com.golearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name="gl_video_like")
@Data
@DynamicInsert
@AllArgsConstructor
public class VideoLike {
    @EmbeddedId
    private VideoCompositeKey videoCompositekey;

    private Date regDt;

    public VideoLike(VideoCompositeKey videoCompositekey){
        this.videoCompositekey = videoCompositekey;
    }
    public VideoLike(){

    }
}
