package com.golearn.repository;

import com.golearn.model.Video;
import com.golearn.model.VideoCompositekey;
import com.golearn.model.VideoSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VideoSpaceRepository extends JpaRepository<VideoSpace, VideoCompositekey> {

}
