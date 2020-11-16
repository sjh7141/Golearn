package kr.co.golearn.repository;

import java.util.List;

import kr.co.golearn.domain.Index;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CourseService {
    @GET("index/{cos_no}")
    Call<List<Index>> getIndexesByCourseNo(@Path("cos_no") long cosNo);
}
