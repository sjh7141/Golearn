package com.golearn.domain;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.golearn.dto.VideoDto;

@Entity(name = "gl_index")
@Table(name = "gl_index")
public class Index {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx_no")
	private long idxNo;
	@Column(name = "cos_no")
	private long cosNo;
	@Column(name = "vid_no")
	private long vidNo;
	@Column(name = "idx_title")
	private String idxTitle;
	@Column(name = "idx_order")
	private int idxOrder;

	@Transient
	private Map<String, Object> map;

	public Index() {

	}

	public Index(long idxNo, long cosNo, long vidNo, String idxTitle, int idxOrder) {
		this.idxNo = idxNo;
		this.cosNo = cosNo;
		this.vidNo = vidNo;
		this.idxTitle = idxTitle;
		this.idxOrder = idxOrder;
	}

	public long getIdxNo() {
		return idxNo;
	}

	public void setIdxNo(long idxNo) {
		this.idxNo = idxNo;
	}

	public long getCosNo() {
		return cosNo;
	}

	public void setCosNo(long cosNo) {
		this.cosNo = cosNo;
	}

	public long getVidNo() {
		return vidNo;
	}

	public void setVidNo(long vidNo) {
		this.vidNo = vidNo;
	}

	public String getIdxTitle() {
		return idxTitle;
	}

	public void setIdxTitle(String idxTitle) {
		this.idxTitle = idxTitle;
	}

	public int getIdxOrder() {
		return idxOrder;
	}

	public void setIdxOrder(int idxOrder) {
		this.idxOrder = idxOrder;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "Index [idxNo=" + idxNo + ", cosNo=" + cosNo + ", vidNo=" + vidNo + ", idxTitle=" + idxTitle
				+ ", idxOrder=" + idxOrder + "]";
	}

}
