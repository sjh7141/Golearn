package com.golearn.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity(name = "gl_video_request")
@Table(name = "gl_video_request")
public class VideoManager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vid_req_no")
	private long vidReqNo;
	@Column(name = "vid_no")
	private long vidNo;
	@Column(name = "mbr_req_no")
	private long mbrReqNo;
	@Column(name = "cos_no")
	private long cosNo;
	@Column(name = "idx_no")
	private long idxNo;
	@Column(name = "vid_req_comment")
	private String vidReqComment;
	@Column(name = "vid_req_accept_yn")
	private int vidReqAcceptYn;
	@Column(name = "mbr_admin_no", nullable = true, insertable = false)
	private Long mbrAdminNo;
	@Column(name = "vid_res_comment")
	private String vidResComment;
	@Column(name = "reg_dt")
	private Date regDt;
	@Column(name = "chg_dt")
	private Date chgDt;

	public VideoManager() {

	}

	public long getVidReqNo() {
		return vidReqNo;
	}

	public void setVidReqNo(long vidReqNo) {
		this.vidReqNo = vidReqNo;
	}

	public long getVidNo() {
		return vidNo;
	}

	public void setVidNo(long vidNo) {
		this.vidNo = vidNo;
	}

	public long getMbrReqNo() {
		return mbrReqNo;
	}

	public void setMbrReqNo(long mbrReqNo) {
		this.mbrReqNo = mbrReqNo;
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

	public Long getMbrAdminNo() {
		return mbrAdminNo;
	}

	public void setMbrAdminNo(Long mbrAdminNo) {
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

	@PrePersist
	void prePersist() {
		this.regDt = this.chgDt = new Date();
		this.vidReqAcceptYn = 0;
	}

	@PreUpdate
	void preUpdate() {
		this.chgDt = new Date();
	}
}
