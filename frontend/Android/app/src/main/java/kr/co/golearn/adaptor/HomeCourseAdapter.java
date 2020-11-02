package kr.co.golearn.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.golearn.R;
import kr.co.golearn.domain.Course;
import kr.co.golearn.util.CommonUtils;

public class HomeCourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private CircularProgressDrawable circularProgressDrawable;
    private ArrayList<Course> courses = null;

    public class HomeCourseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_home_course_title)
        TextView courseTitle;
        @BindView(R.id.card_home_date)
        TextView courseDate;
        @BindView(R.id.card_home_nickname)
        TextView memberNickname;
        @BindView(R.id.card_home_profile)
        ImageView imgProfile;
        @BindView(R.id.card_home_img_thumbnail)
        ImageView imgThumbnail;

        public HomeCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_progressbar)
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public HomeCourseAdapter(ArrayList<Course> list) {
        courses = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_course, parent, false);
            return new HomeCourseViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public int getItemCount() {
        return courses == null ? 0 : courses.size();
    }

    @Override
    public int getItemViewType(int position) {
        return courses.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof HomeCourseViewHolder) {
            populateItemRows((HomeCourseViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }

    }

    private void populateItemRows(HomeCourseViewHolder viewHolder, int position) {
        Course item = courses.get(position);
        viewHolder.courseTitle.setText(item.getCosTitle());
        viewHolder.memberNickname.setText(item.getMbrNickname());
        viewHolder.courseDate.setText(item.getDate());
        circularProgressDrawable = new CircularProgressDrawable(viewHolder.itemView.getContext());
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        Glide.with(viewHolder.itemView.getContext())
                .load(item.getCosThumbnail())
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .into(viewHolder.imgThumbnail);
        Glide.with(viewHolder.itemView.getContext())
                .load(item.getMbrProfile())
                .circleCrop()
                .into(viewHolder.imgProfile);
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed
    }
}
