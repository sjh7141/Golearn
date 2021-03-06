package com.golearn.domain;

import java.util.Date;
import java.util.List;

public class VideoDto {

	private int vidNo;
	private int mbrNo;
	private String mbrNickname; 
	private String mbrProfile; 
	private int vidPno;
	private String vidTitle;
	private String vidContent;
	private String vidUrl;
	private int vidView;
	private Date regDt;
	private boolean vidHide;
	private String vidThumbnail;
	private int vidLength;
	private List<TagDto> tags;

	public int getVidNo() {
		return vidNo;
	}

	public void setVidNo(int vidNo) {
		this.vidNo = vidNo;
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

	public int getVidPno() {
		return vidPno;
	}

	public void setVidPno(int vidPno) {
		this.vidPno = vidPno;
	}

	public String getVidTitle() {
		return vidTitle;
	}

	public void setVidTitle(String vidTitle) {
		this.vidTitle = vidTitle;
	}

	public String getVidContent() {
		return vidContent;
	}

	public void setVidContent(String vidContent) {
		this.vidContent = vidContent;
	}

	public String getVidUrl() {
		return vidUrl;
	}

	public void setVidUrl(String vidUrl) {
		this.vidUrl = vidUrl;
	}

	public int getVidView() {
		return vidView;
	}

	public void setVidView(int vidView) {
		this.vidView = vidView;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public boolean isVidHide() {
		return vidHide;
	}

	public void setVidHide(boolean vidHide) {
		this.vidHide = vidHide;
	}

	public String getVidThumbnail() {
		return vidThumbnail;
	}

	public void setVidThumbnail(String vidThumbnail) {
		this.vidThumbnail = vidThumbnail;
	}

	public int getVidLength() {
		return vidLength;
	}

	public void setVidLength(int vidLength) {
		this.vidLength = vidLength;
	}

	public List<TagDto> getTags() {
		return tags;
	}

	public void setTags(List<TagDto> tags) {
		this.tags = tags;
	}

}
