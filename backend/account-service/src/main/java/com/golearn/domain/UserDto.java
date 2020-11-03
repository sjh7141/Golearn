package com.golearn.domain;

import java.util.Date;

public class UserDto {

	private int no;
	private String username;
	private String password;
	private String email;
	private String nickname;
	private String profile;
	private String banner;
	private Date regDate;
	private Date updateDate;
	private boolean checkEmail;
	private boolean checkActive;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public boolean getCheckEmail() {
		return checkEmail;
	}

	public void setCheckEmail(boolean checkEmail) {
		this.checkEmail = checkEmail;
	}

	public boolean getCheckActive() {
		return checkActive;
	}

	public void setCheckActive(boolean checkActive) {
		this.checkActive = checkActive;
	}

}
