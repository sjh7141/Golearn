package com.golearn.repository;

import com.golearn.model.VideoComment;

import com.golearn.model.VideoCommentPayload;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface VideoCommentRepository extends JpaRepository<VideoComment, Integer> {
    @Query(value = "select p.vid_cmt_no vidCmtNo, p.vid_no vidNo, p.mbr_no mbrNo, p.vid_cmt_pno vid_cmt_pno," +
            " p.vid_comment vidComment, p.reg_dt regDt, p.chg_dt chgDt,count(distinct c.vid_cmt_no) numOfReply " +
            "from gl_video_comment as p left join gl_video_comment as c on p.vid_cmt_no = c.vid_cmt_pno " +
            "where p.vid_no = :vidNo and p.vid_cmt_pno=0 group by p.vid_cmt_no", nativeQuery = true)
    List<VideoCommentPayload> findAllByVidNo(int vidNo, Pageable pageable);
    @Transactional
    int deleteByVidCmtNoAndMbrNo(int vidCmtNo, int mbrNo);

    List<VideoComment> findAllByVidCmtPnoOrderByRegDtAsc(int vidCmtPno);
}
