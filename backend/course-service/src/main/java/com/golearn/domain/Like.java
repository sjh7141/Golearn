package com.golearn.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "gl_course_like")
@Table(name = "gl_course_like")
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "like_no")
	private long likeNo;
	@Column(name = "mbr_no")
	private long mbrNo;
	@Column(name = "cos_no")
	private long cosNo;

	public Like() {

	}

	public Like(long likeNo, long mbrNo, long cosNo) {
		this.likeNo = likeNo;
		this.mbrNo = mbrNo;
		this.cosNo = cosNo;
	}

	public long getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(long likeNo) {
		this.likeNo = likeNo;
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
