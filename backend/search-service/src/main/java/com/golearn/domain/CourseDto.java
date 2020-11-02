package com.golearn.domain;

import java.util.Date;
import java.util.List;

public class CourseDto {

	private int cosNo;
	private int mbrNo;
	private String mbrNickname;
	private String mbrProfile;
	private String cosTitle;
	private String cosContent;
	private String cosThumbnail;
	private int likeCount;
	private Date regDt;
	private Date chgDt;

	public int getCosNo() {
		return cosNo;
	}

	public void setCosNo(int cosNo) {
		this.cosNo = cosNo;
	}

	public int getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(int mbrNo) {
		this.mbrNo = mbrNo;
	}
	
	public String getMbrNickname() {
		return mbrNickname;
	}

	public void setMbrNickname(String mbrNickname) {
		this.mbrNickname = mbrNickname;
	}
	
	public String getMbrProfile() {
		return mbrProfile;
	}

	public void setMbrProfile(String mbrProfile) {
		this.mbrProfile = mbrProfile;
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

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	

}
