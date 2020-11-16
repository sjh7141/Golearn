package kr.co.golearn.domain;

import com.google.gson.annotations.SerializedName;

import kr.co.golearn.domain.response.VideoResponse;

public class Index {
    private long idxNo;
    private long cosNo;
    private long vidNo;
    private String idxTitle;
    private int idxOrder;

    @SerializedName("map")
    private VideoResponse videoResponse;

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

    public VideoResponse getVideoResponse() {
        return videoResponse;
    }

    public void setVideoResponse(VideoResponse videoResponse) {
        this.videoResponse = videoResponse;
    }

    @Override
    public String toString() {
        return "Index{" +
                "idxNo=" + idxNo +
                ", cosNo=" + cosNo +
                ", vidNo=" + vidNo +
                ", idxTitle='" + idxTitle + '\'' +
                ", idxOrder=" + idxOrder +
                ", videoResponse=" + videoResponse +
                '}';
    }
}
