package kr.co.golearn.repository;

import kr.co.golearn.domain.Member;
import kr.co.golearn.domain.request.account.LoginRequest;
import kr.co.golearn.domain.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountService {
    @GET("nickname-check/{mbr_nickname}")
    Call<Boolean> checkNickname(@Path("mbr_nickname") String mbrNickname);

    @GET("id-check/{mbr_id}")
    Call<Boolean> checkId(@Path("mbr_id") String mbrId);

    @POST("users")
    Call<String> join(@Body Member member);
}
