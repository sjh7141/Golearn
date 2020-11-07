package kr.co.golearn.repository;

import kr.co.golearn.domain.request.account.LoginRequest;
import kr.co.golearn.domain.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
