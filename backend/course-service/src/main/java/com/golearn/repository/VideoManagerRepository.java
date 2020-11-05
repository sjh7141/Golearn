package com.golearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.golearn.domain.VideoManager;
import com.golearn.domain.VideoVersioningResopnse;

public interface VideoManagerRepository extends CrudRepository<VideoManager, Long>{
	// 버저닝 생성
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO gl_video_version(idx_no, vid_no) VALUES(:idxNo, :vidNo)", nativeQuery = true)
	void createVersioning(@Param("idxNo") long idxNo, @Param("vidNo") long vidNo);
	
	// 버저닝 목록 가져오기
	@Query(value = "SELECT glvv.ver_no verNo, glvv.idx_no idxNo, glv.vid_no vidNo, glvv.reg_dt regDt, glv.vid_title vidTitle\r\n" + 
			"  		  FROM gl_video_version glvv\r\n" + 
			"	   		   LEFT OUTER JOIN gl_video glv\r\n" + 
			"	   		   ON glvv.vid_no = glv.vid_no\r\n" + 
			" 		 WHERE glvv.idx_no = :idxNo\r\n" +
			" 		 ORDER BY glvv.reg_dt DESC", nativeQuery = true)
	List<VideoVersioningResopnse> getVersioning(@Param("idxNo") long idxNo);
	
	// page 별로 20개씩 가져오기
	@Query(value = "SELECT * FROM gl_video_request WHERE cos_no=:cosNo LIMIT :page, 20", nativeQuery = true)
	List<VideoManager> perPageBy20(@Param("cosNo") long cosNo, @Param("page") int page);
}
