package com.golearn.repository;

import com.golearn.model.VideoCompositeKey;
import com.golearn.model.VideoSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoSpaceRepository extends JpaRepository<VideoSpace, VideoCompositeKey> {

	@Query(value = "SELECT COUNT(*) as count FROM gl_save_video WHERE mbr_no = :mbrNo AND vid_no = :vidNo", nativeQuery = true)
	public int checkMyVideo(@Param("mbrNo") long mbrNo, @Param("vidNo") long vidNo);
}
