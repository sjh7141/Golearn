package kr.co.golearn.domain;

import java.util.Date;

public class Course {
    private long cosNo;
    private long mbrNo;
    private String mbrNickname;
    private String mbrProfile;
    private String cosTitle;
    private String cosContent;
    private String cosThumbnail;
    private int likeCount;
    private Date regDt;
    private Date chgDt;
    private String date;

    public Course() {

    }

    public String getMbrProfile() {
        return mbrProfile;
    }

    public void setMbrProfile(String mbrProfile) {
        this.mbrProfile = mbrProfile;
    }

    public long getCosNo() {
        return cosNo;
    }

    public void setCosNo(long cosNo) {
        this.cosNo = cosNo;
    }

    public long getMbrNo() {
        return mbrNo;
    }

    public void setMbrNo(long mbrNo) {
        this.mbrNo = mbrNo;
    }

    public String getMbrNickname() {
        return mbrNickname;
    }

    public void setMbrNickname(String mbrNickname) {
        this.mbrNickname = mbrNickname;
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

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cosNo=" + cosNo +
                ", mbrNo=" + mbrNo +
                ", mbrNickname='" + mbrNickname + '\'' +
                ", mbrProfile='" + mbrProfile + '\'' +
                ", cosTitle='" + cosTitle + '\'' +
                ", cosContent='" + cosContent + '\'' +
                ", cosThumbnail='" + cosThumbnail + '\'' +
                ", likeCount=" + likeCount +
                ", regDt=" + regDt +
                ", chgDt=" + chgDt +
                ", date='" + date + '\'' +
                '}';
    }
}
