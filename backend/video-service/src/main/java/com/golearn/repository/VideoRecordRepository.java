package com.golearn.repository;

import com.golearn.model.VideoCompositekey;
import com.golearn.model.VideoRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRecordRepository extends JpaRepository<VideoRecord, VideoCompositekey> {
}
