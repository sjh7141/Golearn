package com.golearn.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author HS
 *
 */
@Entity(name = "gl_course_viewer")
@Table(name = "gl_course_viewer")
@IdClass(value = CourseViewerKey.class)
public class CourseViewer {
	@Id
	@Column(name = "mbr_no")
	private long mbrNo;
	@Id
	@Column(name = "cos_no")
	private long cosNo;
	@Id
	@Column(name = "idx_no")
	private long idxNo;

	public CourseViewer() {

	}

	public CourseViewer(long mbrNo, long cosNo, long idxNo) {
		this.mbrNo = mbrNo;
		this.cosNo = cosNo;
		this.idxNo = idxNo;
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

	public long getIdxNo() {
		return idxNo;
	}

	public void setIdxNo(long idxNo) {
		this.idxNo = idxNo;
	}

	@Override
	public String toString() {
		return "CourseViewer [mbrNo=" + mbrNo + ", cosNo=" + cosNo + ", idxNo=" + idxNo + "]";
	}
	
	
}
