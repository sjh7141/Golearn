package com.golearn.dto;

import java.util.Date;

public class VideoRequestDto {

	private int vidReqNo;
	private int vidNo;
	private int mbrReqNo;
	private int cosNo;
	private int idxNo;
	private String vidReqComment;
	private int vidReqAcceptYn;
	private int mbrAdminNo;
	private String vidResComment;
	private Date regDt;
	private Date chgDt;

	public int getVidReqNo() {
		return vidReqNo;
	}

	public void setVidReqNo(int vidReqNo) {
		this.vidReqNo = vidReqNo;
	}

	public int getVidNo() {
		return vidNo;
	}

	public void setVidNo(int vidNo) {
		this.vidNo = vidNo;
	}

	public int getMbrReqNo() {
		return mbrReqNo;
	}

	public void setMbrReqNo(int mbrReqNo) {
		this.mbrReqNo = mbrReqNo;
	}

	public int getCosNo() {
		return cosNo;
	}

	public void setCosNo(int cosNo) {
		this.cosNo = cosNo;
	}

	public int getIdxNo() {
		return idxNo;
	}

	public void setIdxNo(int idxNo) {
		this.idxNo = idxNo;
	}

	public String getVidReqComment() {
		return vidReqComment;
	}

	public void setVidReqComment(String vidReqComment) {
		this.vidReqComment = vidReqComment;
	}

	public int getVidReqAcceptYn() {
		return vidReqAcceptYn;
	}

	public void setVidReqAcceptYn(int vidReqAcceptYn) {
		this.vidReqAcceptYn = vidReqAcceptYn;
	}

	public int getMbrAdminNo() {
		return mbrAdminNo;
	}

	public void setMbrAdminNo(int mbrAdminNo) {
		this.mbrAdminNo = mbrAdminNo;
	}

	public String getVidResComment() {
		return vidResComment;
	}

	public void setVidResComment(String vidResComment) {
		this.vidResComment = vidResComment;
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
