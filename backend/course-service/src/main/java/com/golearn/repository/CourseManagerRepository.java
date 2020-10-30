package com.golearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.golearn.domain.CourseManager;
import com.golearn.domain.CourseManagerResopnse;

public interface CourseManagerRepository extends CrudRepository<CourseManager, Long>{
	// 코스 관리자 생성
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO gl_course_manager(mbr_no, cos_no, authority) VALUES(:mbrNo, :cosNo, :authority)", nativeQuery = true)
	void save(@Param("mbrNo") long mbrNo, @Param("cosNo") long cosNo, @Param("authority") String authority);
	
	// 코스 관리자 삭제
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM gl_course_manager WHERE mbr_no=:mbrNo AND cos_no=:cosNo", nativeQuery = true)
	void delete(@Param("mbrNo") long mbrNo, @Param("cosNo") long cosNo);
	
	// 코스 관리자 목록 조회
	@Query(value="SELECT glcm.mbr_no mbrNo, mbr_nickname mbrNickname, mbr_profile mbrProfile, mbr_id mbrId, mbr_email mbrEmail, glcm.reg_dt regDt, glcm.authority\r\n" + 
			"       FROM gl_course_manager glcm\r\n" + 
			"		    LEFT OUTER JOIN gl_member glm\r\n" + 
			"             ON glm.mbr_no = glcm.mbr_no\r\n" + 
			"      WHERE glcm.cos_no = :cosNo", nativeQuery = true)
	List<CourseManagerResopnse> findManagers(@Param("cosNo") long cosNo);
	
	// 코스 관리자를 추가하기위한 검색
	@Query(value="SELECT mbr_no mbrNo, mbr_nickname mbrNickname, mbr_profile mbrProfile, mbr_id mbrId, mbr_email mbrEmail\r\n" + 
			"       FROM gl_member gm\r\n" + 
			"      WHERE gm.mbr_no NOT IN(SELECT mbr_no\r\n" + 
			"						        FROM gl_course_manager\r\n" + 
			"						       WHERE cos_no = :cosNo)\r\n" + 
			"            AND gm.mbr_id LIKE CONCAT(:search,'%')", nativeQuery = true)
	List<CourseManagerResopnse> findManagersExceptCourse(@Param("cosNo") long cosNo, @Param("search") String search);
}
