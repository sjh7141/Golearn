package kr.co.golearn.domain.response;

import com.google.gson.annotations.SerializedName;

public class VideoLikeState {
    @SerializedName("isLike")
    private boolean isLike;

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    @Override
    public String toString() {
        return "VideoLikeState{" +
                "isLike=" + isLike +
                '}';
    }
}
