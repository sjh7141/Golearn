package com.golearn.repository;


import com.golearn.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video,Integer> {

    @Query(value="update Video set vidHide=1 where mbrNo = :mbrNo",nativeQuery = true)
    boolean hideVideo(int vidNo, int mbrNo);
}
