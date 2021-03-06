package com.golearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "gl_video_record")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VideoRecord {
    @EmbeddedId
    private VideoCompositeKey videoCompositekey;

    private int vid_rec_time;

}
