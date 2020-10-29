package com.golearn.repository;


import com.golearn.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update gl_video set vid_hide=true where mbr_no = :mbrNo and vid_no = :vidNo", nativeQuery = true)
    int hideVideo(@Param("vidNo") int vidNo, @Param("mbrNo") int mbrNo);

    @Transactional
    @Modifying
    @Query(value = "update gl_video set vid_view=vid_view+1 where vid_no=:vidNo", nativeQuery = true)
    void addViewCount(@Param("vidNo") int vidNo);

    @Query(value = "select v.* from gl_video v left join gl_save_video vs on v.vid_no = vs.vid_no where v.mbr_no=:mbrNo", nativeQuery = true)
    List<Video> findAllSpaceVideoByMbrNo(@Param("mbrNo") int mbrNo);

    @Query(value = "select v.* from gl_video as v right join (select * from gl_video_like where mbr_no=:mbrNo) as l on v.vid_no=l.vid_no", nativeQuery = true)
    List<Video> findAllByMbrNoAndLikeVideo(int mbrNo);

    List<Video> findAllByMbrNo(int mbrNo);
}
