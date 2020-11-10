package kr.co.golearn.adaptor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import kr.co.golearn.R;
import kr.co.golearn.domain.Author;
import kr.co.golearn.domain.Comment;
import kr.co.golearn.domain.Member;
import kr.co.golearn.domain.Tag;
import kr.co.golearn.domain.Video;
import kr.co.golearn.domain.response.VideoResponse;
import kr.co.golearn.util.PreferenceManager;
import kr.co.golearn.view.main.channel.ChannelActivity;
import kr.co.golearn.view.video.CommentReplyBottomFragment;
import kr.co.golearn.view.video.ReplyBottomFragment;
import kr.co.golearn.viewmodel.TextViewModel;
import kr.co.golearn.viewmodel.VideoViewModel;

public class VideoCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;
    private final String[] longClickItems = new String[]{"수정하기", "삭제하기"};

    private ArrayList<Comment> comments = null;
    private VideoResponse videoResponse = null;
    private boolean alreadyChipSettings = false;
    private Context context;
    private TextViewModel textViewModel;
    private VideoViewModel videoViewModel;
    private long vidNo;
    private long mbrNo;

    public VideoCommentAdapter(ArrayList<Comment> list, TextViewModel tViewModel, Context context, long vidNo) {
        comments = list;
        textViewModel = tViewModel;
        this.context = context;
        this.vidNo = vidNo;
        mbrNo = new PreferenceManager().getLong(context, PreferenceManager.USER_NO);
    }

    public void setVideoResponse(VideoResponse videoResponse) {
        this.videoResponse = videoResponse;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_video_detail_comment_header_txt_title)
        TextView videoTitle;
        @BindView(R.id.card_video_detail_comment_header_txt_view_count)
        TextView viewCount;
        @BindView(R.id.card_video_detail_comment_header_txt_reg_dt)
        TextView regDate;
        @BindView(R.id.card_video_detail_comment_header_chip_group)
        ChipGroup chipGroup;
        @BindView(R.id.card_video_detail_comment_header_img_profile)
        ImageView videoProfile;
        @BindView(R.id.card_video_detail_comment_header_txt_nickname)
        TextView videoNickname;
        @BindView(R.id.card_video_detail_comment_header_txt_subscriper_count)
        TextView subscriperCount;
        @BindView(R.id.card_video_detail_comment_header_comment_img_profile)
        ImageView userProfile;
        @BindView(R.id.card_video_detail_comment_header_thumb_up)
        CheckBox thumbUpCheckBox;
        @BindView(R.id.card_video_detail_comment_header_txt_like)
        TextView videoLikeCount;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            videoViewModel = ViewModelProviders.of((FragmentActivity) context).get(VideoViewModel.class);
            videoViewModel.getIsVideoLike().observe((FragmentActivity) context, videoLikeState -> {
                thumbUpCheckBox.setChecked(videoLikeState.isLike());
            });
            videoViewModel.getOkVideoLike().observe((FragmentActivity) context, okVideoLike -> {
                if (okVideoLike) {
                    videoLikeCount.setText(Integer.parseInt(videoLikeCount.getText().toString()) + 1 + "");
                }
            });
            videoViewModel.getNoVideoLike().observe((FragmentActivity) context, noVideoLike -> {
                if (noVideoLike) {
                    videoLikeCount.setText(Integer.parseInt(videoLikeCount.getText().toString()) - 1 + "");
                }
            });
            videoViewModel.getVideoLikeStateFromServer(context, vidNo);
        }

        public void bind(VideoResponse videoResponse) {
            Author author = videoResponse.getAuthor();
            Video video = videoResponse.getVideo();
            List<Tag> tags = videoResponse.getVideo().getTags();

            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(itemView.getContext());
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();
            Glide.with(itemView.getContext())
                    .load(author.getMbrProfile())
                    .circleCrop()
                    .placeholder(circularProgressDrawable)
                    .into(videoProfile);
            Glide.with(itemView.getContext())
                    .load(new PreferenceManager().getString(itemView.getContext(), PreferenceManager.USER_PROFILE))
                    .circleCrop()
                    .placeholder(circularProgressDrawable)
                    .into(userProfile);
            videoTitle.setText(video.getVidTitle());
            viewCount.setText(video.getViewCount());
            regDate.setText(video.getDate());
            videoNickname.setText(author.getMbrNickName());
            videoLikeCount.setText(video.getVidLikes() + "");
            subscriperCount.setText("구독자 " + 0 + "명");
            if (!alreadyChipSettings) {
                for (Tag tag : tags) {
                    Chip chip = new Chip(itemView.getContext());
                    chip.setText(tag.getTag_name());
                    chip.setClickable(false);
                    chipGroup.addView(chip);
                }
                alreadyChipSettings = true;
            }
        }

        @OnClick(R.id.card_video_detail_comment_header_layout_comment)
        void clickToReply() {
            ReplyBottomFragment fragment = ReplyBottomFragment.newInstance(textViewModel);
            fragment.show(((FragmentActivity) itemView.getContext()).getSupportFragmentManager(), "test");
        }

        @OnClick(R.id.card_video_detail_comment_header_thumb_up)
        void clickToLike() {
            if (thumbUpCheckBox.isChecked()) {
                videoViewModel.okLikeVideoFromServer(context, vidNo);
            } else {
                videoViewModel.noLikeVideoFromServer(context, vidNo);
            }
        }

        @OnClick(R.id.card_video_detail_comment_header_img_profile)
        void clickToProfile() {
            Author author = videoResponse.getAuthor();
            Intent intent = new Intent(itemView.getContext(), ChannelActivity.class);
            intent.putExtra("mbrNo", author.getMbrNo());
            itemView.getContext().startActivity(intent);
            ((Activity) itemView.getContext()).finish();
        }
    }

    public class VideoCommentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_video_detail_comment_item_profile)
        ImageView profile;
        @BindView(R.id.card_video_detail_comment_item_nickname)
        TextView nickname;
        @BindView(R.id.card_video_detail_comment_item_reg_dt)
        TextView regDt;
        @BindView(R.id.card_video_detail_comment_item_comment)
        TextView comment;
        @BindView(R.id.card_video_detail_comment_item_img_reply)
        ImageView imgReply;
        @BindView(R.id.card_video_detail_comment_item_reply_count)
        TextView commentCount;
        @BindView(R.id.card_video_detail_comment_item_card_view)
        MaterialCardView cardView;

        public VideoCommentHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Comment comment) {
            Member member = comment.getMember();
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(itemView.getContext());
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();

            Glide.with(itemView.getContext())
                    .load(member.getMbrProfile())
                    .circleCrop()
                    .placeholder(circularProgressDrawable)
                    .into(profile);
            nickname.setText(member.getMbrNickname());
            regDt.setText(comment.getDate());
            this.comment.setText(comment.getVidComment());
            commentCount.setText(comment.getNumOfReply() + "");
        }

        @OnClick(R.id.card_video_detail_comment_item_card_view)
        void clickToCardView() {
            int position = this.getAdapterPosition() - 1;
            Comment comment = comments.get(position);
            CommentReplyBottomFragment fragment = CommentReplyBottomFragment.newInstance(comment.getVidNo(), comment.getVidCmtNo());
            fragment.show(((FragmentActivity) itemView.getContext()).getSupportFragmentManager(), "comment_reply");
        }

        @OnLongClick(R.id.card_video_detail_comment_item_card_view)
        void longClickToCardView() {
            int position = this.getAdapterPosition() - 1;
            Comment comment = comments.get(position);
            if (mbrNo == comment.getMember().getMbrNo()) {
                new MaterialAlertDialogBuilder(itemView.getContext())
                        .setItems(longClickItems, (dialogInterface, i) -> {
                            switch (i) {
                                case 0:
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    final View customLayout = View.inflate(context, R.layout.fragment_bottom_reply, null);
                                    EditText editText = customLayout.findViewById(R.id.bottom_reply_edt_comment);
                                    ImageView okImage = customLayout.findViewById(R.id.bottom_reply_btn_ok);
                                    ImageView profile = customLayout.findViewById(R.id.bottom_reply_img_profile);
                                    Glide.with(customLayout)
                                            .load(new PreferenceManager().getString(context, PreferenceManager.USER_PROFILE))
                                            .circleCrop()
                                            .into(profile);
                                    okImage.setVisibility(View.GONE);
                                    editText.setText(comment.getVidComment());
                                    builder.setView(customLayout);
                                    builder.setPositiveButton("수정완료", (dialog, which) -> {
                                        String text = editText.getText().toString();
                                        videoViewModel.updateComment(context, comment.getVidCmtNo(), text);
                                        comment.setVidComment(text);
                                        comments.set(position, comment);
                                        notifyDataSetChanged();
                                    });
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                    break;
                                case 1:
                                    new MaterialAlertDialogBuilder(itemView.getContext())
                                            .setTitle("정말 삭제하시겠습니까?")
                                            .setNeutralButton("되돌아가기", (dialogInterface1, i1) -> {
                                            })
                                            .setPositiveButton("삭제하기", (dialogInterface1, i1) -> {
                                                videoViewModel.deleteComment(itemView.getContext(), comment.getVidCmtNo());
                                                comments.remove(position);
                                                notifyItemRemoved(position);
                                                notifyDataSetChanged();
                                            }).show();
                                    break;
                            }
                        }).show();
            }
        }

        @OnClick(R.id.card_video_detail_comment_item_profile)
        void clickToProfile() {
            int position = this.getAdapterPosition() - 1;
            Comment comment = comments.get(position);
            Intent intent = new Intent(itemView.getContext(), ChannelActivity.class);
            intent.putExtra("mbrNo", comment.getMbrNo());
            itemView.getContext().startActivity(intent);
            ((Activity) itemView.getContext()).finish();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video_detail_comment_header, parent, false);
            holder = new VideoCommentAdapter.HeaderViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video_detail_comment_item, parent, false);
            holder = new VideoCommentAdapter.VideoCommentHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VideoCommentAdapter.HeaderViewHolder) {
            VideoCommentAdapter.HeaderViewHolder headerViewHolder = (VideoCommentAdapter.HeaderViewHolder) holder;
            headerViewHolder.bind(videoResponse);
        } else {
            VideoCommentAdapter.VideoCommentHolder homeVideoPagerHolder = (VideoCommentAdapter.VideoCommentHolder) holder;
            homeVideoPagerHolder.bind(comments.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return comments.size() + 1;
    }
}
