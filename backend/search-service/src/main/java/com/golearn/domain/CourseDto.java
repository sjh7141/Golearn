package com.golearn.domain;

import java.util.Date;

public class CourseDto {

	private long cosNo;
	private long mbrNo;
	private String cosTitle;
	private String cosContent;
	private String cosThumbnail;
	private Date regDt;
	private Date chgDt;

	public long getCosNo() {
		return cosNo;
	}

	public void setCosNo(long cosNo) {
		this.cosNo = cosNo;
	}

	public long getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(long mbrNo) {
		this.mbrNo = mbrNo;
	}

	public String getCosTitle() {
		return cosTitle;
	}

	public void setCosTitle(String cosTitle) {
		this.cosTitle = cosTitle;
	}

	public String getCosContent() {
		return cosContent;
	}

	public void setCosContent(String cosContent) {
		this.cosContent = cosContent;
	}

	public String getCosThumbnail() {
		return cosThumbnail;
	}

	public void setCosThumbnail(String cosThumbnail) {
		this.cosThumbnail = cosThumbnail;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public Date getChgDt() {
		return chgDt;
	}

	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}

}
