package kr.co.golearn.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kr.co.golearn.domain.Member;
import kr.co.golearn.repository.AccountService;
import kr.co.golearn.repository.RetrofitClient;
import kr.co.golearn.util.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private String TAG = this.getClass().toString();

    private MutableLiveData<Member> member;

    public LiveData<Member> getMember(){
        if(member == null){
            member = new MutableLiveData<>();
        }
        return member;
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
}
