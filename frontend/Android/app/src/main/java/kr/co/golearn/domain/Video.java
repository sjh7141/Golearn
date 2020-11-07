package kr.co.golearn.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Video {
    private long vidNo;
    private long mbrNo;
    private long vidPno;
    private String vidTitle;
    private String vidContent;
    private String vidUrl;
    private int vidView;
    private Date regDt;
    private boolean vidHide;
    private String vidThumbnail;
    private int vidLength;
    private int vidLikes;
    private String date;
    private String videoLength;
    private String viewCount;

    @SerializedName("tags")
    private List<Tag> tags;

    public Video() {

    }

    public long getVidNo() {
        return vidNo;
    }

    public void setVidNo(long vidNo) {
        this.vidNo = vidNo;
    }

    public long getMbrNo() {
        return mbrNo;
    }

    public void setMbrNo(long mbrNo) {
        this.mbrNo = mbrNo;
    }

    public long getVidPno() {
        return vidPno;
    }

    public void setVidPno(long vidPno) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(String videoLength) {
        this.videoLength = videoLength;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
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

    @Override
    public String toString() {
        return "Video{" +
                "vidNo=" + vidNo +
                ", mbrNo=" + mbrNo +
                ", vidPno=" + vidPno +
                ", vidTitle='" + vidTitle + '\'' +
                ", vidContent='" + vidContent + '\'' +
                ", vidUrl='" + vidUrl + '\'' +
                ", vidView=" + vidView +
                ", regDt=" + regDt +
                ", vidHide=" + vidHide +
                ", vidThumbnail='" + vidThumbnail + '\'' +
                ", vidLength=" + vidLength +
                ", date='" + date + '\'' +
                ", videoLength='" + videoLength + '\'' +
                ", viewCount='" + viewCount + '\'' +
                ", tags=" + tags +
                '}';
    }
}