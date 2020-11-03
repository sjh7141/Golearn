package com.golearn.model;

import lombok.Data;
import lombok.ToString;



@Data
@ToString
public class CourseManagerResponse {
    private int mbrNo;
    private String mbrId;
    private String regDt;
    private String mbrProfile;
    private String authority;
}
