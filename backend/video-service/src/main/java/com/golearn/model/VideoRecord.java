package com.golearn.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "gl_video_record")
@NoArgsConstructor
@AllArgsConstructor
public class VideoRecord {
    @EmbeddedId
    private VideoCompositeKey videoCompositekey;

    private int vid_rec_time;

}
