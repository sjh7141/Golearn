package kr.co.golearn.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import kr.co.golearn.domain.Index;
import kr.co.golearn.domain.UseIndexDto;
import kr.co.golearn.domain.Video;
import kr.co.golearn.domain.response.VideoResponse;
import kr.co.golearn.repository.CourseService;
import kr.co.golearn.repository.RetrofitClient;
import kr.co.golearn.repository.VideoService;
import kr.co.golearn.util.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseViewModel extends ViewModel {
    private String TAG = this.getClass().toString();
    private MutableLiveData<List<UseIndexDto>> useIndexDtoList;
    private MutableLiveData<List<Index>> indexes;

    public LiveData<List<UseIndexDto>> useIndexDtoList(){
        if(useIndexDtoList == null){
            useIndexDtoList = new MutableLiveData<>();
        }
        return useIndexDtoList;
    }

    public LiveData<List<Index>> indexes(){
        if(indexes == null){
            indexes = new MutableLiveData<>();
        }
        return indexes;
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
                        VideoResponse videoResponse = index.getVideoResponse();
                        Video video = videoResponse.getVideo();
                        video.setDate(CommonUtils.calcTimeDate(video.getRegDt()));
                        video.setVideoLength(CommonUtils.convertVideoTime(video.getVidLength()));
                        video.setViewCount(CommonUtils.convertCount(video.getVidView()));
                    }
                    indexes.setValue(indexList);
                }
            }

            @Override
            public void onFailure(Call<List<Index>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
