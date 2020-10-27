package com.golearn.repository;

import com.golearn.model.Video;
import com.golearn.model.VideoSpace;
import com.golearn.model.VideoSpaceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoSpaceRepository extends JpaRepository<VideoSpace, VideoSpaceId> {
    @Query(value = "select * from Video v, VideoSpace vs where vs.mbrNo = :mbrNo and v.vidNo = vs.vidNo",nativeQuery = true)
    List<Video> findAllByMbrNo(int mbrNo);
}
