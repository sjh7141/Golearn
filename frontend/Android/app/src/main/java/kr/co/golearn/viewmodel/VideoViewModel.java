package kr.co.golearn.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import kr.co.golearn.domain.Video;
import kr.co.golearn.repository.RetrofitClient;
import kr.co.golearn.repository.VideoService;
import kr.co.golearn.util.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoViewModel extends ViewModel {
    private String TAG = this.getClass().toString();

    private MutableLiveData<List<Video>> videos;

    public LiveData<List<Video>> getVideos(){
        if(videos == null){
            videos = new MutableLiveData<>();
        }
        return videos;
    }

    // 내 비디오 들고오기
    public void getVideosFromServer(Context context){
        String token =  new PreferenceManager().getString(context, PreferenceManager.TOKEN_KEY);
        VideoService videoService = RetrofitClient.videoService();
        Call<List<Video>> vidListCall = videoService.getVideo(token);
        vidListCall.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if(response.isSuccessful()){
                    videos.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
