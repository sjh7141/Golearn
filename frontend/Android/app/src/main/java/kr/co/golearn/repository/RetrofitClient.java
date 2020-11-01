package kr.co.golearn.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kr.co.golearn.util.CommonUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static AuthService authService() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(CommonUtils.BASE_URL + CommonUtils.AUTH_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(AuthService.class);
    }

    public static AccountService accountService() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(CommonUtils.BASE_URL + CommonUtils.ACCOUNT_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(AccountService.class);
    }
}
