package com.golearn.dto;

import java.util.Date;
import java.util.List;

public class VideoDto {
	private int vidNo;
	private int mbrNo;
	private int vidPno;
	private String vidTitle;
	private String vidContent;
	private String vidUrl;
	private int vidView;
	private Date regDt;
	private boolean vidHide;
	private String vidThumbnail;
	private int vidLength;
	private List<Tag> tags;
	private int vidLikes;

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

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public int getVidLikes() {
		return vidLikes;
	}

	public void setVidLikes(int vidLikes) {
		this.vidLikes = vidLikes;
	}

}
