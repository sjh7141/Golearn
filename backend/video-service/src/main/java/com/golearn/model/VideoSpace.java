package com.golearn.model;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Data
@Entity(name="gl_save_video")
public class VideoSpace {
    @EmbeddedId
    private VideoSpaceId videoSpaceId;

}
