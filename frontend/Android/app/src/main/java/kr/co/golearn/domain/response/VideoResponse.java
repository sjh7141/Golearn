package kr.co.golearn.domain.response;

import com.google.gson.annotations.SerializedName;

import kr.co.golearn.domain.Author;
import kr.co.golearn.domain.Video;

public class VideoResponse {

    @SerializedName("author")
    private Author author;

    @SerializedName("video")
    private Video video;

    public VideoResponse() {

    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "VideoResponse{" +
                "author=" + author +
                ", video=" + video +
                '}';
    }
}
