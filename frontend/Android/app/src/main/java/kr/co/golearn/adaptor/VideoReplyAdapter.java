package kr.co.golearn.adaptor;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import kr.co.golearn.R;
import kr.co.golearn.domain.Comment;
import kr.co.golearn.domain.Member;
import kr.co.golearn.util.PreferenceManager;
import kr.co.golearn.viewmodel.VideoViewModel;

public class VideoReplyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Comment> comments = null;
    private final String[] longClickItems = new String[]{"수정하기", "삭제하기"};
    private Context context;
    private VideoViewModel videoViewModel;

    public class VideoReplyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_video_detail_comment_item_reply_profile)
        ImageView profile;
        @BindView(R.id.card_video_detail_comment_item_reply_nickname)
        TextView nickname;
        @BindView(R.id.card_video_detail_comment_item_reply_reg_dt)
        TextView regDt;
        @BindView(R.id.card_video_detail_comment_item_reply_comment)
        TextView comment;
        private long mbrNo;

        public VideoReplyViewHolder(@NonNull View itemView) {
            super(itemView);
            mbrNo = new PreferenceManager().getLong(itemView.getContext(), PreferenceManager.USER_NO);
            ButterKnife.bind(this, itemView);
            videoViewModel = ViewModelProviders.of((FragmentActivity) context).get(VideoViewModel.class);

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
        }

        @OnLongClick(R.id.card_video_detail_comment_reply_item_card_view)
        void longClickCardView() {
            int position = this.getAdapterPosition();
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
    }

    public VideoReplyAdapter(ArrayList<Comment> list, Context context) {
        comments = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video_detail_comment_reply_item, parent, false);
        return new VideoReplyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VideoReplyAdapter.VideoReplyViewHolder viewHolder = (VideoReplyAdapter.VideoReplyViewHolder) holder;
        viewHolder.bind(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments == null ? 0 : comments.size();
    }


}