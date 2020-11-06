package com.golearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="gl_tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    private int tagNo;
    private String tagName;
}
