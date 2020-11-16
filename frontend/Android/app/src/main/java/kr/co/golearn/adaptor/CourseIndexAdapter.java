package kr.co.golearn.adaptor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.golearn.R;
import kr.co.golearn.domain.Index;
import kr.co.golearn.domain.UseIndexDto;
import kr.co.golearn.view.video.VideoActivity;

public class CourseIndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Index> indexes = null;

    public CourseIndexAdapter(ArrayList<Index> list){
        this.indexes = list;
    }

    public class CourseIndexViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.card_pager_video_thumbnail)
        ImageView videoThumbnail;
        @BindView(R.id.card_pager_video_txt_time)
        TextView videoTime;
        @BindView(R.id.card_pager_video_item_title)
        TextView videoTitle;
        @BindView(R.id.card_pager_video_item_nickname)
        TextView videoNickname;
        @BindView(R.id.card_pager_video_item_view)
        TextView videoViewCount;
        @BindView(R.id.card_pager_video_item_time)
        TextView videoRegTime;

        public CourseIndexViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Index index){
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(itemView.getContext());
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();

            Glide.with(itemView.getContext())
                    .load(index.getVideoResponse().getVideo().getVidThumbnail())
                    .centerCrop()
                    .placeholder(circularProgressDrawable)
                    .into(videoThumbnail);
            videoTime.setText(index.getVideoResponse().getVideo().getVideoLength());
            videoTitle.setText(index.getIdxTitle());
            videoNickname.setText(index.getVideoResponse().getAuthor().getMbrNickName());
            videoViewCount.setText(index.getVideoResponse().getVideo().getViewCount());
            videoRegTime.setText(index.getVideoResponse().getVideo().getDate());
        }

        @OnClick(R.id.card_pager_video_card_view)
        void clickToVideo(){
            Index index = indexes.get(this.getAdapterPosition());
            Intent intent = new Intent(itemView.getContext(), VideoActivity.class);
            intent.putExtra("vidNo", index.getVidNo());
            itemView.getContext().startActivity(intent);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pager_video_item, parent, false);
        return new CourseIndexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CourseIndexAdapter.CourseIndexViewHolder viewHolder = (CourseIndexAdapter.CourseIndexViewHolder) holder;
        viewHolder.bind(indexes.get(position));
    }

    @Override
    public int getItemCount() {
        return indexes == null ? 0 : indexes.size();
    }
}
