package kr.co.golearn.repository;

import java.util.List;

import kr.co.golearn.domain.Comment;
import kr.co.golearn.domain.Video;
import kr.co.golearn.domain.response.VideoLikeState;
import kr.co.golearn.domain.response.VideoResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VideoService {
    @GET(".")
    Call<List<Video>> getVideos(@Header("Authorization") String token);

    @GET("{vid_no}")
    Call<VideoResponse> getVideo(@Path("vid_no") long vidNo);

    @GET("comment/{vid_no}")
    Call<List<Comment>> getPnoComments(@Path("vid_no") long vidNo, @Query("page_no") int page);

    @POST("comment/{vid_no}")
    Call<Comment> postPnoComment(@Header("Authorization") String token, @Path("vid_no") long vidNo, @Body Comment comment);

    @GET("comment/{vid_no}/{vid_cmt_pno}")
    Call<List<Comment>> getCommentReplies(@Path("vid_no") long vidNo, @Path("vid_cmt_pno") long vidCmtPno);

    @POST("comment/{vid_no}/{vid_cmt_no}")
    Call<Comment> postCommentReply(@Header("Authorization") String token, @Path("vid_no") long vidNo, @Path("vid_cmt_no") long vidCmtPno, @Body Comment comment);

    @GET("like/{vid_no}")
    Call<VideoLikeState> stateVideoLike(@Header("Authorization") String token, @Path("vid_no") long vidNo);

    @POST("like/{vid_no}")
    Call<Void> likeVideo(@Header("Authorization") String token, @Path("vid_no") long vidNo);

    @DELETE("like/{vid_no}")
    Call<Void> cancelLikeVideo(@Header("Authorization") String token, @Path("vid_no") long vidNo);

    @DELETE("comment/{vid_cmt_no}")
    Call<Void> deleteComment(@Header("Authorization") String token, @Path("vid_cmt_no") long vidCmtNo);

    @PUT("comment/{vid_cmt_no}")
    Call<Comment> putPnoComment(@Header("Authorization") String token, @Path("vid_cmt_no") long vidCmtNo, @Body Comment comment);

    @GET("member/{mbr_no}")
    Call<List<Video>> getVideosByMemberNo(@Path("mbr_no") long mbrNo);
}
