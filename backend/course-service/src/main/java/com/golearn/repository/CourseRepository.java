package com.golearn.repository;

import java.util.List;

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
	
	public List<Course> findByMbrNo(long id);
}
