package com.golearn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class TagCompositeKey implements Serializable {
    @ManyToOne
    @JoinColumn(name="vid_no")
    private Video video;
    @ManyToOne
    @JoinColumn(name="tag_no")
    private Tag tag;

}