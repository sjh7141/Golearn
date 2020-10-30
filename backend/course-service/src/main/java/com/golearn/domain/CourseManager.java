package com.golearn.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
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
	@Column(name = "reg_dt")
	private Date regDt;
	@Column(name = "authority")
	private String authority;

	public CourseManager() {

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

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@PrePersist
	void prePersist() {
		this.regDt = new Date();
	}
}
