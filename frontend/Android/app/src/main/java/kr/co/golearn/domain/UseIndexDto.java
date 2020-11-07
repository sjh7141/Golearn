package kr.co.golearn.domain;

import kr.co.golearn.domain.response.VideoResponse;

public class UseIndexDto {
    private Index index;
    private VideoResponse videoResponse;

    public UseIndexDto(Index index, VideoResponse videoResponse) {
        this.index = index;
        this.videoResponse = videoResponse;
    }

    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }

    public VideoResponse getVideoResponse() {
        return videoResponse;
    }

    public void setVideoResponse(VideoResponse videoResponse) {
        this.videoResponse = videoResponse;
    }

    @Override
    public String toString() {
        return "UseIndexDto{" +
                "index=" + index +
                ", videoResponse=" + videoResponse +
                '}';
    }
}
