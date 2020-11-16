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
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.golearn.R;
import kr.co.golearn.domain.Member;
import kr.co.golearn.domain.Video;
import kr.co.golearn.view.video.VideoActivity;

public class HomeVideoPagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;
    private final int TYPE_FOOTER = 2;
    private ArrayList<Video> videos = null;
    private Member member = null;

    public HomeVideoPagerAdapter(ArrayList<Video> list) {
        videos = list;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pager_home_header_banner)
        ImageView imgBanner;
        @BindView(R.id.pager_home_header_nickname)
        TextView memberNickname;
        @BindView(R.id.pager_home_header_profile)
        ImageView imgProfile;
        @BindView(R.id.pager_home_header_subscriper)
        TextView subscriper;
        @BindView(R.id.pager_home_header_toggle_subscription)
        MaterialCardView btnSubscription;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Member member) {
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(itemView.getContext());
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();
            Glide.with(itemView.getContext())
                    .load(member.getBanner())
                    .centerCrop()
                    .placeholder(circularProgressDrawable)
                    .into(imgBanner);
            Glide.with(itemView.getContext())
                    .load(member.getProfile())
                    .circleCrop()
                    .placeholder(circularProgressDrawable)
                    .into(imgProfile);
            memberNickname.setText(member.getNickname());
            subscriper.setText("구독자 " + member.getSubscribeCount() + "명");
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_pager_card_view)
        MaterialCardView cardView;

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class HomeVideoPagerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_pager_home_video_thumbnail)
        ImageView videoThumbnail;
        @BindView(R.id.card_pager_home_video_txt_time)
        TextView videoTime;
        @BindView(R.id.card_pager_home_video_item_title)
        TextView videoTitle;
        @BindView(R.id.card_pager_home_video_item_nickname)
        TextView nickname;
        @BindView(R.id.card_pager_home_video_item_view)
        TextView viewCount;
        @BindView(R.id.card_pager_home_video_item_time)
        TextView videoRegTime;

        public HomeVideoPagerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Video video) {
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(itemView.getContext());
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();
            Glide.with(itemView.getContext())
                    .load(video.getVidThumbnail())
                    .centerCrop()
                    .placeholder(circularProgressDrawable)
                    .into(videoThumbnail);
            videoTime.setText(video.getVideoLength());
            videoTitle.setText(video.getVidTitle());
            nickname.setText(member.getNickname());
            viewCount.setText(video.getViewCount());
            videoRegTime.setText(video.getDate());
        }

        @OnClick(R.id.card_pager_home_video_card_view)
        void clickToVideo() {
            Video video = videos.get(this.getAdapterPosition() - 1);
            Intent intent = new Intent(itemView.getContext(), VideoActivity.class);
            intent.putExtra("vidNo", video.getVidNo());
            itemView.getContext().startActivity(intent);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else if (position == videos.size() + 1)
            return TYPE_FOOTER;
        else
            return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pager_home_video_header, parent, false);
            holder = new HeaderViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pager_home_video_footer, parent, false);
            holder = new FooterViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pager_home_video_item, parent, false);
            holder = new HomeVideoPagerHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.bind(member);
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
        } else {
            HomeVideoPagerHolder homeVideoPagerHolder = (HomeVideoPagerHolder) holder;
            homeVideoPagerHolder.bind(videos.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return videos.size() + 2;
    }
}
