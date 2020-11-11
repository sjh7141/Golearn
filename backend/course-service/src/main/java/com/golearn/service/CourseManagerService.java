package com.golearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golearn.domain.CourseManager;
import com.golearn.domain.CourseManagerResopnse;
import com.golearn.repository.CourseManagerRepository;

@Service
public class CourseManagerService {
	@Autowired
	private CourseManagerRepository courseManagerRepository;
	
	// 코스 관리자 생성
	public void save(CourseManager request) {
		courseManagerRepository.save(request.getMbrNo(), request.getCosNo(), request.getAuthority());
	}
	
	// 코스 관리자 삭제
	public void delete(CourseManager request) {
		courseManagerRepository.delete(request.getMbrNo(), request.getCosNo());
	}
	
	// 코스 관리자 목록 조회
	public List<CourseManagerResopnse> findManagers(long cosNo){
		return courseManagerRepository.findManagers(cosNo);
	}
	
	// 코스 관리자를 추가하기위한 검색
	public List<CourseManagerResopnse> searchToAddManagers(long cosNo, String search){
		return courseManagerRepository.findManagersExceptCourse(cosNo, search);
	}
	
	public int checkManager(long cosNo, long mbrNo) {
		return courseManagerRepository.checkManager(cosNo, mbrNo);
	}
}
