package com.golearn.domain;

public class LoadmapCourseDto {

	private int no;
	private int cosNo;
	private int ldmNo;
	private int mbrNo;
	private int order;
	private String cosTitle;
	private String cosContent;
	private String cosThumbnail;
	private String cosBanner;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getCosNo() {
		return cosNo;
	}

	public void setCosNo(int cosNo) {
		this.cosNo = cosNo;
	}

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

	public String getCosBanner() {
		return cosBanner;
	}

	public void setCosBanner(String cosBanner) {
		this.cosBanner = cosBanner;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}
