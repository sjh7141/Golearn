package kr.co.golearn.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import kr.co.golearn.domain.Comment;
import kr.co.golearn.domain.Video;
import kr.co.golearn.domain.response.VideoLikeState;
import kr.co.golearn.domain.response.VideoResponse;
import kr.co.golearn.repository.RetrofitClient;
import kr.co.golearn.repository.VideoService;
import kr.co.golearn.util.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoViewModel extends ViewModel {
    private String TAG = this.getClass().toString();

    private MutableLiveData<List<Video>> videos;
    private MutableLiveData<VideoResponse> video;
    private MutableLiveData<List<Comment>> comments;
    private MutableLiveData<List<Comment>> replies;
    private MutableLiveData<VideoLikeState> isVideoLike;
    private MutableLiveData<Boolean> okVideoLike;
    private MutableLiveData<Boolean> noVideoLike;
    private MutableLiveData<Comment> oKComment;

    public LiveData<List<Video>> getVideos() {
        if (videos == null) {
            videos = new MutableLiveData<>();
        }
        return videos;
    }

    public LiveData<VideoResponse> getVideo() {
        if (video == null) {
            video = new MutableLiveData<>();
        }
        return video;
    }

    public LiveData<List<Comment>> getComments() {
        if (comments == null) {
            comments = new MutableLiveData<>();
        }
        return comments;
    }

    public LiveData<List<Comment>> getReplies() {
        if (replies == null) {
            replies = new MutableLiveData<>();
        }
        return replies;
    }

    public LiveData<VideoLikeState> getIsVideoLike() {
        if (isVideoLike == null) {
            isVideoLike = new MutableLiveData<>();
        }
        return isVideoLike;
    }

    public LiveData<Boolean> getOkVideoLike() {
        if (okVideoLike == null) {
            okVideoLike = new MutableLiveData<>();
        }
        return okVideoLike;
    }

    public LiveData<Boolean> getNoVideoLike() {
        if (noVideoLike == null) {
            noVideoLike = new MutableLiveData<>();
        }
        return noVideoLike;
    }

    public LiveData<Comment> getOkComment() {
        if (oKComment == null) {
            oKComment = new MutableLiveData<>();
        }
        return oKComment;
    }

    // 내 비디오 들고오기
    public void getVideosFromServer(Context context) {
        String token = new PreferenceManager().getString(context, PreferenceManager.TOKEN_KEY);
        VideoService videoService = RetrofitClient.videoService();
        Call<List<Video>> vidListCall = videoService.getVideos(token);
        vidListCall.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if (response.isSuccessful()) {
                    videos.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // 영상 조회
    public void getVideoFromServer(long vidNo) {
        VideoService videoService = RetrofitClient.videoService();
        Call<VideoResponse> videoCall = videoService.getVideo(vidNo);
        videoCall.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if (response.isSuccessful()) {
                    video.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // 영상 댓글 리스트
    public void getCommentsFromServer(long vidNo, int page) {
        VideoService commentsService = RetrofitClient.videoService();
        Call<List<Comment>> commentsCall = commentsService.getPnoComments(vidNo, page);
        commentsCall.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    comments.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // 영상 댓글 입력
    public void postCommentToServer(Context context, long vidNo, String text) {
        Comment comment = new Comment();
        comment.setVidComment(text);
        String token = new PreferenceManager().getString(context, PreferenceManager.TOKEN_KEY);
        VideoService commentService = RetrofitClient.videoService();
        Call<Comment> commentCall = commentService.postPnoComment(token, vidNo, comment);
        commentCall.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    oKComment.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // 영상 대댓글 리스트
    public void getRepliesFromServer(long vidNo, long vidCmtPno) {
        VideoService repliesService = RetrofitClient.videoService();
        Call<List<Comment>> repliesCall = repliesService.getCommentReplies(vidNo, vidCmtPno);
        repliesCall.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    replies.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // 영상 대댓글 작성
    public void postCommentReplyToServer(Context context, long vidNo, long vidCmtPno, String text) {
        Comment comment = new Comment();
        comment.setVidComment(text);
        String token = new PreferenceManager().getString(context, PreferenceManager.TOKEN_KEY);
        VideoService commentService = RetrofitClient.videoService();
        Call<Comment> commentCall = commentService.postCommentReply(token, vidNo, vidCmtPno, comment);
        commentCall.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    oKComment.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // 비디오 좋아요 여부
    public void getVideoLikeStateFromServer(Context context, long vidNo) {
        String token = new PreferenceManager().getString(context, PreferenceManager.TOKEN_KEY);
        VideoService likeService = RetrofitClient.videoService();
        Call<VideoLikeState> likeCall = likeService.stateVideoLike(token, vidNo);
        likeCall.enqueue(new Callback<VideoLikeState>() {
            @Override
            public void onResponse(Call<VideoLikeState> call, Response<VideoLikeState> response) {
                if (response.isSuccessful()) {
                    isVideoLike.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<VideoLikeState> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // 비디오 좋아요
    public void okLikeVideoFromServer(Context context, long vidNo) {
        String token = new PreferenceManager().getString(context, PreferenceManager.TOKEN_KEY);
        VideoService likeService = RetrofitClient.videoService();
        Call<Void> likeCall = likeService.likeVideo(token, vidNo);
        likeCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    okVideoLike.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // 비디오 좋아요 취소
    public void noLikeVideoFromServer(Context context, long vidNo) {
        String token = new PreferenceManager().getString(context, PreferenceManager.TOKEN_KEY);
        VideoService likeService = RetrofitClient.videoService();
        Call<Void> likeCall = likeService.cancelLikeVideo(token, vidNo);
        likeCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    noVideoLike.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // 댓글 삭제
    public void deleteComment(Context context, long vidCmtNo) {
        String token = new PreferenceManager().getString(context, PreferenceManager.TOKEN_KEY);
        VideoService deleteCommentService = RetrofitClient.videoService();
        Call<Void> deleteCommentCall = deleteCommentService.deleteComment(token, vidCmtNo);
        deleteCommentCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    Toast.makeText(context, "댓글이 삭제되었습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // 댓글 업데이트
    public void updateComment(Context context, long vidCmtNo, String text) {
        Comment comment = new Comment();
        comment.setVidComment(text);
        String token = new PreferenceManager().getString(context, PreferenceManager.TOKEN_KEY);
        VideoService updateCommentService = RetrofitClient.videoService();
        Call<Comment> updateCommentCall = updateCommentService.putPnoComment(token, vidCmtNo, comment);
        updateCommentCall.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    Toast.makeText(context, "댓글이 수정되었습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // 유저별 업로드 동영상 조회
    public void getVideoByMemberNo(long mbrNo) {
        VideoService videoService = RetrofitClient.videoService();
        Call<List<Video>> videoCall = videoService.getVideosByMemberNo(mbrNo);
        videoCall.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if (response.isSuccessful()) {
                    videos.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
