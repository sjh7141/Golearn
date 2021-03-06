package com.golearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golearn.domain.Course;
import com.golearn.mapper.CourseMapper;
import com.golearn.repository.CourseManagerRepository;
import com.golearn.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseManagerRepository courseManagerRepository;
	
	@Autowired
	private CourseMapper courseMapper;
	
	// 코스 20개씩 목록 조회
	public List<Course> perPageBy20(int page) {
		page = (page - 1) * 20;
		List<Course> courses = courseRepository.perPageBy20(page);
		return courses;
	}
	
	// 코스 상세 조회
	public Course findById(int cosNo) {
		Course course = courseRepository.findById(cosNo);
		course.setLikeCount(courseMapper.findCourseLikeCount(cosNo));
		course.setViewerCount(courseMapper.findCourseViewerCount(cosNo));
		return course;
	}
	
	// 코스 생성
	public Course save(String mbrNo) {
		Course course = new Course();
		course.setMbrNo(Long.parseLong(mbrNo));
		course = courseRepository.save(course);
		courseManagerRepository.save(course.getMbrNo(), course.getCosNo(), "Master");
		return course;
	}
	
	// 코스 수정
	public Course update(String mbrNo, Course course) {
		long userNo = Long.parseLong(mbrNo);
		course.setMbrNo(userNo);
		return courseRepository.save(course);
	}
	
	public List<Course> findByMbrNo(long id){
		return courseRepository.findByMbrNoOrderByRegDtDesc(id);
	}
	
}
