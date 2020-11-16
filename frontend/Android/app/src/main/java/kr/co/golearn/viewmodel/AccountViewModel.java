package kr.co.golearn.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kr.co.golearn.domain.Member;
import kr.co.golearn.domain.request.account.LoginRequest;
import kr.co.golearn.domain.response.LoginResponse;
import kr.co.golearn.repository.AccountService;
import kr.co.golearn.repository.AuthService;
import kr.co.golearn.repository.RetrofitClient;
import kr.co.golearn.util.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountViewModel extends ViewModel {
    private String TAG = this.getClass().toString();

    private MutableLiveData<Boolean> isSaveID;
    private MutableLiveData<Boolean> isAutoLogin;
    private MutableLiveData<Boolean> successLogin;
    private MutableLiveData<Boolean> failLogin;
    private MutableLiveData<Boolean> successCheckId;
    private MutableLiveData<Boolean> failCheckId;
    private MutableLiveData<String> responseJoin;
    private MutableLiveData<Member> member;

    public LiveData<Boolean> getIsSaveID() {
        if (isSaveID == null) {
            isSaveID = new MutableLiveData<>();
        }
        return isSaveID;
    }

    public LiveData<Boolean> getIsAutoLogin() {
        if (isAutoLogin == null) {
            isAutoLogin = new MutableLiveData<>();
        }
        return isAutoLogin;
    }

    public LiveData<Boolean> getSuccessLogin() {
        if (successLogin == null) {
            successLogin = new MutableLiveData<>();
        }
        return successLogin;
    }

    public LiveData<Boolean> getFailLogin() {
        if (failLogin == null) {
            failLogin = new MutableLiveData<>();
        }
        return failLogin;
    }

    public LiveData<Boolean> getSuccessCheckId() {
        if (successCheckId == null) {
            successCheckId = new MutableLiveData<>();
        }
        return successCheckId;
    }

    public LiveData<Boolean> getFailCheckId() {
        if (failCheckId == null) {
            failCheckId = new MutableLiveData<>();
        }
        return failCheckId;
    }

    public LiveData<String> getResponseJoin() {
        if (responseJoin == null) {
            responseJoin = new MutableLiveData<>();
        }
        return responseJoin;
    }

    public LiveData<Member> getMember(){
        if(member == null){
            member = new MutableLiveData<>();
        }
        return member;
    }

    // 아이디 저장 했는지
    public void getSaveID(Context context) {
        isSaveID.setValue(new PreferenceManager().getBoolean(context, PreferenceManager.IS_SAVED_ID));
    }

    // 자동 로그인 저장 했는지
    public void getAutoLogin(Context context) {
        isAutoLogin.setValue(new PreferenceManager().getBoolean(context, PreferenceManager.IS_AUTO_LOGIN));
    }

    // 로그인 시도
    public void login(Context context, String id, String password) {
        AuthService loginService = RetrofitClient.authService();
        Call<LoginResponse> loginCall = loginService.login(new LoginRequest(id, password));
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse result = response.body();
                    successLogin.setValue(true);
                    // 토큰 저장
                    new PreferenceManager().setString(context, PreferenceManager.TOKEN_KEY, result.getToken());
                } else {
                    failLogin.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                failLogin.setValue(true);
            }
        });
    }

    // 닉네임 중복 확인
    public void checkId(String id) {
        AccountService idService = RetrofitClient.accountService();
        Call<Boolean> idCall = idService.checkId(id);
        idCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body()) {
                    successCheckId.setValue(true);
                } else {
                    failCheckId.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                failCheckId.setValue(true);
            }
        });
    }

    // 회원가입
    public void join(String id, String password) {
        Member member = new Member(id, id, password);
        AccountService joinService = RetrofitClient.accountService();
        Call<String> joinCall = joinService.join(member);
        joinCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                responseJoin.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // 사용자 정보 조회
    public void getMember(Context context){
        String token =  new PreferenceManager().getString(context, PreferenceManager.TOKEN_KEY);
        AccountService memberService = RetrofitClient.accountService();
        Call<Member> memberCall = memberService.getMemberDetail(token);
        memberCall.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                if(response.isSuccessful()){
                    member.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // 사용자 정보 조회
    public void getMemberByNo(long mbrNo){
        AccountService memberService = RetrofitClient.accountService();
        Call<Member> memberCall = memberService.getMemberByNo(mbrNo);
        memberCall.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                if(response.isSuccessful()){
                    member.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
