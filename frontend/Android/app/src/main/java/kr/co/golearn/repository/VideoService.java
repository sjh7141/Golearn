package kr.co.golearn.repository;

import java.util.List;

import kr.co.golearn.domain.Video;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface VideoService {
    @GET(".")
    Call<List<Video>> getVideo(@Header("Authorization") String token);
}
