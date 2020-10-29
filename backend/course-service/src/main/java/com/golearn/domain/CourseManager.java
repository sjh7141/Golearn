package com.golearn.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity(name = "gl_course_manager")
@Table(name = "gl_course_manager")
@IdClass(value = CourseManagerKey.class)
public class CourseManager {
	@Id
	@Column(name = "mbr_no")
	private long mbrNo;
	@Id
	@Column(name = "cos_no")
	private long cosNo;

	public CourseManager() {

	}

	public CourseManager(long mbrNo, long cosNo) {
		this.mbrNo = mbrNo;
		this.cosNo = cosNo;
	}

	public long getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(long mbrNo) {
		this.mbrNo = mbrNo;
	}

	public long getCosNo() {
		return cosNo;
	}

	public void setCosNo(long cosNo) {
		this.cosNo = cosNo;
	}

}
