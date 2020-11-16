package kr.co.golearn.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Comment {
    private long vidCmtNo;
    private long vidNo;
    private long mbrNo;
    private long vidCmtPno;
    private String vidComment;
    private Date regDt;
    private Date chgDt;
    private int numOfReply;
    private String date;

    @SerializedName("member")
    private Member member;

    public Comment() {
    }

    public long getVidCmtNo() {
        return vidCmtNo;
    }

    public void setVidCmtNo(long vidCmtNo) {
        this.vidCmtNo = vidCmtNo;
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

    public long getVidCmtPno() {
        return vidCmtPno;
    }

    public void setVidCmtPno(long vidCmtPno) {
        this.vidCmtPno = vidCmtPno;
    }

    public String getVidComment() {
        return vidComment;
    }

    public void setVidComment(String vidComment) {
        this.vidComment = vidComment;
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

    public int getNumOfReply() {
        return numOfReply;
    }

    public void setNumOfReply(int numOfReply) {
        this.numOfReply = numOfReply;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "vidCmtNo=" + vidCmtNo +
                ", vidNo=" + vidNo +
                ", mbrNo=" + mbrNo +
                ", vidCmtPno=" + vidCmtPno +
                ", vidComment='" + vidComment + '\'' +
                ", regDt=" + regDt +
                ", chgDt=" + chgDt +
                ", numOfReply=" + numOfReply +
                ", member=" + member +
                '}';
    }
}
