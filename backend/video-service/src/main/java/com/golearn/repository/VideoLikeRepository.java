package com.golearn.repository;

import com.golearn.model.VideoLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoLikeRepository extends JpaRepository<VideoLike, Integer> {
    boolean existsVideoLikeByVidNoAndMbrNo(int vidNo, int mbrNo);
    void deleteByVidNoAndMbrNo(int vidNo, int mbrNo);
}
