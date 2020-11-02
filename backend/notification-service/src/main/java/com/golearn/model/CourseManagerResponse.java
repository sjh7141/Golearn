package com.golearn.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Data
@ToString
public class CourseManagerResponse {
    private long mbrNo;
    private String mbrId;
    private String regDt;
    private String mbrProfile;
    private String authority;
}
