package com.golearn.domain;

import java.util.Date;

public class LoadmapDto {

	private int ldmNo;
	private int mbrNo;
	private String ldmContent;
	private String ldmTuhmbnail;
	private Date regDt;
	private Date chgDt;

	public int getLdmNo() {
		return ldmNo;
	}

	public void setLdmNo(int ldmNo) {
		this.ldmNo = ldmNo;
	}

	public int getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(int mbrNo) {
		this.mbrNo = mbrNo;
	}

	public String getLdmContent() {
		return ldmContent;
	}

	public void setLdmContent(String ldmContent) {
		this.ldmContent = ldmContent;
	}

	public String getLdmTuhmbnail() {
		return ldmTuhmbnail;
	}

	public void setLdmTuhmbnail(String ldmTuhmbnail) {
		this.ldmTuhmbnail = ldmTuhmbnail;
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
