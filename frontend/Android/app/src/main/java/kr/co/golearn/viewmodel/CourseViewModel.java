package kr.co.golearn.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import kr.co.golearn.domain.Index;
import kr.co.golearn.domain.UseIndexDto;
import kr.co.golearn.domain.response.VideoResponse;
import kr.co.golearn.repository.CourseService;
import kr.co.golearn.repository.RetrofitClient;
import kr.co.golearn.repository.VideoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseViewModel extends ViewModel {
    private String TAG = this.getClass().toString();
    private MutableLiveData<List<UseIndexDto>> useIndexDtoList;

    public LiveData<List<UseIndexDto>> useIndexDtoList(){
        if(useIndexDtoList == null){
            useIndexDtoList = new MutableLiveData<>();
        }
        return useIndexDtoList;
    }

    // 목차 목록 조회
    public void getIndexes(long cosNo){
        List<UseIndexDto> result = new ArrayList<>();
        CourseService indexesService = RetrofitClient.CourseService();
        Call<List<Index>> indexesCall = indexesService.getIndexesByCourseNo(cosNo);
        indexesCall.enqueue(new Callback<List<Index>>() {
            @Override
            public void onResponse(Call<List<Index>> call, Response<List<Index>> response) {
                if(response.isSuccessful()){
                    List<Index> indexList = response.body();
                    for(Index index : indexList){
                        VideoResponse video = getVideoForIndex(index.getVidNo());
                        result.add(new UseIndexDto(index, video));
                    }
                    useIndexDtoList.setValue(result);
                }
            }

            @Override
            public void onFailure(Call<List<Index>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }


    // 목차에 대한 영상 상세정보 가져오기
    private VideoResponse getVideoForIndex(long vidNo){
        VideoService videoService = RetrofitClient.videoService();
        Call<VideoResponse> videoCall = videoService.getVideo(vidNo);
        VideoResponse result = null;
        try {
            result = videoCall.execute().body();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return result;
    }
}
