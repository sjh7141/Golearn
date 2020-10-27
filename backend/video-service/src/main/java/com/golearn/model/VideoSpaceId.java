package com.golearn.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class VideoSpaceId implements Serializable {
    private int vidNo;
    private int mbrNo;
}
