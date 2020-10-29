package com.golearn.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.golearn.domain.Like;

public interface LikeReqository extends CrudRepository<Like, Long>{
	// 좋아요
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO gl_course_like VALUES(:mbrNo, :cosNo)", nativeQuery = true)
	void save(@Param("mbrNo") long mbrNo, @Param("cosNo") long cosNo);
	
	// 좋아요 취소
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM gl_course_like WHERE mbr_no=:mbrNo AND cos_no=:cosNo", nativeQuery = true)
	void delete(@Param("mbrNo") long mbrNo, @Param("cosNo") long cosNo);
}
