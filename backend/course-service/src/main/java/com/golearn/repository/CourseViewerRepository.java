package com.golearn.repository;

import com.golearn.domain.CoursePreference;
import com.golearn.domain.CourseViewer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.golearn.domain.Like;

import java.util.List;

public interface CourseViewerRepository extends CrudRepository<CourseViewer, Long>{
	// 목차 수강 완료
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO gl_course_viewer VALUES(:mbrNo, :cosNo, :idxNo)", nativeQuery = true)
	void save(@Param("mbrNo") long mbrNo, @Param("cosNo") long cosNo, @Param("idxNo") long idxNo);

	@Query(value = "select * from gl_course_viewer where mbr_no = :mbrNo group by cos_no", nativeQuery = true)
	List<CourseViewer> findAllByMbrNo(long mbrNo);
}
