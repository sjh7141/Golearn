package kr.co.golearn.repository;

import kr.co.golearn.domain.response.SearchCourseResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    // 검색 목록 가져오기
    @GET("v1/search")
    Call<SearchCourseResponse> searchFromCourse(@Query("page_no") int page, @Query("search") String search, @Query("type") String type);
}
