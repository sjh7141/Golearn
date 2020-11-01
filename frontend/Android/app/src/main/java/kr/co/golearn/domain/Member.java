package kr.co.golearn.domain;

import java.util.Date;

public class Member {
    private long no;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String profile;
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

    @Override
    public String toString() {
        return "Member{" +
                "no=" + no +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", profile='" + profile + '\'' +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", checkActive=" + checkActive +
                ", checkEmail=" + checkEmail +
                '}';
    }
}
