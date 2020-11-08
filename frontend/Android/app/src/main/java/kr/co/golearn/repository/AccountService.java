package kr.co.golearn.repository;

import kr.co.golearn.domain.Member;
import kr.co.golearn.domain.request.account.LoginRequest;
import kr.co.golearn.domain.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountService {

    // 닉네임 중복 체크
    @GET("nickname-check/{mbr_nickname}")
    Call<Boolean> checkNickname(@Path("mbr_nickname") String mbrNickname);

    // 아이디 중복 체크
    @GET("id-check/{mbr_id}")
    Call<Boolean> checkId(@Path("mbr_id") String mbrId);

    // 회원가입
    @POST("users")
    Call<String> join(@Body Member member);

    @GET("users")
    Call<Member> getMemberDetail(@Header("Authorization") String token);

    @GET("users/no/{mbr_no}")
    Call<Member> getMemberByNo(@Path("mbr_no") long mbrNo);
}
