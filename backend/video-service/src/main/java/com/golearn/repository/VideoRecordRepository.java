package com.golearn.repository;

import com.golearn.model.VideoCompositeKey;
import com.golearn.model.VideoRecord;
import com.golearn.model.VideoRecordPayload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRecordRepository extends JpaRepository<VideoRecord, VideoCompositeKey> {
//    @Query(value = "select * from gl_video_record where mbr_no = :mbrNo and vid_no in (:vidNos)" , nativeQuery = true)
//    List<VideoRecord> findAllByMbrNo(@Param("mbrNo") int mbrNo, @Param("vidNos") List<Integer> vidNos);
    @Query(value = "select vid_no vidNo ,vid_rec_time vidRecTime from gl_video_record where mbr_no = :mbrNo and vid_no in (:vidNos)" , nativeQuery = true)
    List<VideoRecordPayload> findAllByMbrNo(@Param("mbrNo") int mbrNo, @Param("vidNos") List<Integer> vidNos);
}
