package kr.co.golearn.domain;

import java.util.Date;

public class Member {
    private long no;
    private long mbrNo;
    private String username;
    private String mbrId;
    private String nickname;
    private String mbrNickname;
    private String password;
    private String mbrEmail;
    private String email;
    private String profile;
    private String mbrProfile;
    private String banner;
    private Date regDate;
    private Date updateDate;
    private boolean checkActive;
    private boolean checkEmail;

    public Member() {

    }

    public Member(String username, String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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

    public boolean isCheckActive() {
        return checkActive;
    }

    public void setCheckActive(boolean checkActive) {
        this.checkActive = checkActive;
    }

    public boolean isCheckEmail() {
        return checkEmail;
    }

    public void setCheckEmail(boolean checkEmail) {
        this.checkEmail = checkEmail;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public long getMbrNo() {
        return mbrNo;
    }

    public void setMbrNo(long mbrNo) {
        this.mbrNo = mbrNo;
    }

    public String getMbrId() {
        return mbrId;
    }

    public void setMbrId(String mbrId) {
        this.mbrId = mbrId;
    }

    public String getMbrEmail() {
        return mbrEmail;
    }

    public void setMbrEmail(String mbrEmail) {
        this.mbrEmail = mbrEmail;
    }

    public String getMbrProfile() {
        return mbrProfile;
    }

    public void setMbrProfile(String mbrProfile) {
        this.mbrProfile = mbrProfile;
    }

    public String getMbrNickname() {
        return mbrNickname;
    }

    public void setMbrNickname(String mbrNickname) {
        this.mbrNickname = mbrNickname;
    }

    @Override
    public String toString() {
        return "Member{" +
                "no=" + no +
                ", mbrNo=" + mbrNo +
                ", username='" + username + '\'' +
                ", mbrId='" + mbrId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", mbrEmail='" + mbrEmail + '\'' +
                ", email='" + email + '\'' +
                ", profile='" + profile + '\'' +
                ", mbrProfile='" + mbrProfile + '\'' +
                ", banner='" + banner + '\'' +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", checkActive=" + checkActive +
                ", checkEmail=" + checkEmail +
                '}';
    }
}
