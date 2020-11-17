package com.golearn.repository;

import java.util.List;

import com.golearn.domain.CoursePrefer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.golearn.domain.Course;

public interface CourseRepository extends CrudRepository<Course, Long>{
	
	// page 별로 20개씩 가져오기
	@Query(value = "SELECT * FROM gl_course LIMIT :page, 20", nativeQuery = true)
	List<Course> perPageBy20(@Param("page") int page);
	
	// 코스 상세정보 가져오기
	Course findById(long id);
	
	boolean existsByCosNoAndMbrNo(long cosNo, long mbrNo);

	public List<Course> findByMbrNoOrderByRegDtDesc(long id);

	@Query(value = "select  g1.mbr_no as mbrNo, g2.cos_no as cosNo,(g1.mbr_no, g2.cos_no)in (select mbr_no,cos_no from gl_course_viewer) as value from gl_course_viewer as g1 join gl_course_viewer g2 group by g1.mbr_no, g2.cos_no having g1.mbr_no = :mbrNo",nativeQuery = true)
	List<CoursePrefer> getPrefer(@Param("mbrNo") long mbrNo);
}


