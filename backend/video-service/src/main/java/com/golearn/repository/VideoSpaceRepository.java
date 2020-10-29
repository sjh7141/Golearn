package com.golearn.repository;

import com.golearn.model.VideoCompositeKey;
import com.golearn.model.VideoSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoSpaceRepository extends JpaRepository<VideoSpace, VideoCompositeKey> {

}
