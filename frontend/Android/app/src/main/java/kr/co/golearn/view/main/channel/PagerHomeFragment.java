package kr.co.golearn.view.main.channel;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.golearn.R;
import kr.co.golearn.adaptor.HomeVideoPagerAdapter;
import kr.co.golearn.domain.Video;
import kr.co.golearn.util.CommonUtils;
import kr.co.golearn.util.ProgressLoadingDialog;
import kr.co.golearn.viewmodel.AccountViewModel;
import kr.co.golearn.viewmodel.VideoViewModel;

public class PagerHomeFragment extends Fragment {

    @BindView(R.id.pager_home_video_recyclerview)
    RecyclerView recyclerViewVideo;

    private long mbrNo;

    private ProgressLoadingDialog progressLoadingDialog;
    private HomeVideoPagerAdapter homeVideoPagerAdapter;
    private GridLayoutManager gridLayoutManager;
    private AccountViewModel accountViewModel;
    private ArrayList<Video> originVideos;
    private VideoViewModel videoViewModel;

    public PagerHomeFragment(long mbrNo){
        this.mbrNo = mbrNo;
    }

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
        actionViewModel();
    }

    private void actionViewModel() {
        progressLoadingDialog.show();
        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        accountViewModel.getMember().observe(getActivity(), member -> {
            homeVideoPagerAdapter.setMember(member);
            recyclerViewVideo.setAdapter(homeVideoPagerAdapter);
        });
        videoViewModel.getVideos().observe(getActivity(), videos -> {
            for (Video video : videos) {
                video.setDate(CommonUtils.calcTimeDate(video.getRegDt()));
                video.setViewCount(CommonUtils.convertCount(video.getVidView()));
                video.setVideoLength(CommonUtils.convertVideoTime(video.getVidLength()));
                originVideos.add(video);
            }
            homeVideoPagerAdapter.notifyDataSetChanged();
            progressLoadingDialog.dismiss();
        });
        accountViewModel.getMemberByNo(mbrNo);
        videoViewModel.getVideoByMemberNo(mbrNo);
    }

    private void init() {
        progressLoadingDialog = new ProgressLoadingDialog(this.getContext());
        progressLoadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        originVideos = new ArrayList<>();
        homeVideoPagerAdapter = new HomeVideoPagerAdapter(originVideos);
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerViewVideo.setLayoutManager(gridLayoutManager);
    }
}
