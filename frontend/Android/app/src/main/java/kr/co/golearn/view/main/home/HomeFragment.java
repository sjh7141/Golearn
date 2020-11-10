package kr.co.golearn.view.main.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.golearn.R;
import kr.co.golearn.adaptor.HomeCourseAdapter;
import kr.co.golearn.domain.Course;
import kr.co.golearn.domain.response.SearchCourseResponse;
import kr.co.golearn.repository.RetrofitClient;
import kr.co.golearn.repository.SearchService;
import kr.co.golearn.util.CommonUtils;
import retrofit2.Call;

public class HomeFragment extends Fragment {

    private String TAG = this.getClass().toString();

    @BindView(R.id.home_recycler_view)
    RecyclerView recyclerViewCourse;
    @BindView(R.id.home_refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.home_main_layout)
    ConstraintLayout homeMainLayout;

    private HomeCourseAdapter homeCourseAdapter;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<Course> courses;
    private String searchText = "";
    private boolean isLoading;
    private int scrollPosition;
    private int currentSize;
    private int page;

    // Fragment View 생성시 ButterKnife 바인딩
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        init();

        populateData(page++);
        initScrollListener();
        initRefreshListener();
    }

    private void initRefreshListener() {
        refreshLayout.setOnRefreshListener(() -> {
            afterInit();
            refreshLayout.setRefreshing(false);
        });
    }

    private void initScrollListener() {
        recyclerViewCourse.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                    if (courses.size() < (page - 1) * 12) {
                        Snackbar.make(homeMainLayout, "모든 코스를 들고왔습니다", Snackbar.LENGTH_SHORT).show();
                        isLoading = true;
                        return;
                    }
                    if (gridLayoutManager != null && gridLayoutManager.findLastCompletelyVisibleItemPosition() == courses.size() - 1) {
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        courses.add(null);
        recyclerViewCourse.post(() -> homeCourseAdapter.notifyItemInserted(courses.size() - 1));

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            courses.remove(courses.size() - 1);
            scrollPosition = courses.size();
            recyclerViewCourse.post(() -> homeCourseAdapter.notifyItemRemoved(scrollPosition));
            currentSize = scrollPosition;
            populateData(page++);
            recyclerViewCourse.post(() -> homeCourseAdapter.notifyDataSetChanged());
            isLoading = false;
        }, 1000);
    }


    private void populateData(int page) {
        SearchService courseService = RetrofitClient.searchService();
        Call<SearchCourseResponse> courseCall = courseService.searchFromCourse(page, searchText, "course");
        SearchCourseResponse result = null;
        try {
            result = courseCall.execute().body();
        } catch (Exception e) {

        }
        if (result != null && result.getCourse().size() != 0) {
            for (Course course : result.getCourse()) {
                course.setDate(CommonUtils.calcTimeDate(course.getRegDt()));
                courses.add(course);
            }
            if (page == 1) {
                homeCourseAdapter.notifyDataSetChanged();
            }
        } else {
            Snackbar.make(homeMainLayout, "모든 코스를 들고왔습니다", Snackbar.LENGTH_SHORT).show();
        }
    }

    // 검색 및 리스트 목록 초기화
    private void init() {
        page = 1;
        isLoading = false;
        courses = new ArrayList<>();
        homeCourseAdapter = new HomeCourseAdapter(courses);
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerViewCourse.setLayoutManager(gridLayoutManager);
        recyclerViewCourse.setAdapter(homeCourseAdapter);
    }

    public void searchToCourse(String searchText) {
        this.searchText = searchText;
        afterInit();
    }

    private void afterInit() {
        if (!courses.isEmpty()) {
            courses.clear();
        }
        page = 1;
        isLoading = false;
        homeCourseAdapter.notifyDataSetChanged();
        initScrollListener();
        populateData(page++);

    }
}
