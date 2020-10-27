package com.golearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Data
@Entity(name="gl_save_video")
@AllArgsConstructor
@NoArgsConstructor
public class VideoSpace {
    @EmbeddedId
    private VideoCompositekey videoCompositekey;

}
