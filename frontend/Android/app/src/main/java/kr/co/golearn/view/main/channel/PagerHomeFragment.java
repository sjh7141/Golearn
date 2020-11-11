package kr.co.golearn.view.main.channel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.golearn.R;
import kr.co.golearn.adaptor.HomeVideoPagerAdapter;
import kr.co.golearn.domain.Video;
import kr.co.golearn.util.CommonUtils;
import kr.co.golearn.viewmodel.MainViewModel;
import kr.co.golearn.viewmodel.VideoViewModel;

public class PagerHomeFragment extends Fragment {

    @BindView(R.id.pager_home_video_recyclerview)
    RecyclerView recyclerViewVideo;

    private MainViewModel accountViewModel;
    private VideoViewModel videoViewModel;
    private HomeVideoPagerAdapter homeVideoPagerAdapter;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<Video> originVideos;
    private int page;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        populateData(page++);
        actionViewModel();
    }

    private void actionViewModel() {
        accountViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);

        accountViewModel.getMember().observe(getActivity(), member -> {
            homeVideoPagerAdapter.setMember(member);
            recyclerViewVideo.setAdapter(homeVideoPagerAdapter);
        });
        videoViewModel.getVideos().observe(getActivity(), videos -> {
            for (Video video : videos) {
                video.setDate(CommonUtils.calcTimeDate(video.getRegDt()));
                video.setViewCount(CommonUtils.convertCount(video.getVidView()));
                originVideos.add(video);
            }
            homeVideoPagerAdapter.notifyDataSetChanged();
        });
        accountViewModel.getMember(getContext());
        videoViewModel.getVideosFromServer(getContext());
    }

    private void populateData(int page) {

    }

    private void init() {
        page = 1;
        originVideos = new ArrayList<>();
        homeVideoPagerAdapter = new HomeVideoPagerAdapter(originVideos);
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerViewVideo.setLayoutManager(gridLayoutManager);
    }
}
