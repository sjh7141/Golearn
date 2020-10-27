package com.golearn.repository;

import com.golearn.model.VideoCompositekey;
import com.golearn.model.VideoLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoLikeRepository extends JpaRepository<VideoLike, VideoCompositekey> {
}
