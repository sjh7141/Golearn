package com.golearn.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.golearn.domain.Like;

public interface CourseViewerRepository extends CrudRepository<Like, Long>{
	// 목차 수강 완료
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO gl_course_viewer VALUES(:mbrNo, :cosNo, :idxNo)", nativeQuery = true)
	void save(@Param("mbrNo") long mbrNo, @Param("cosNo") long cosNo, @Param("idxNo") long idxNo);
}
