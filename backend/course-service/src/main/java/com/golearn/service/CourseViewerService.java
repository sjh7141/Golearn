package com.golearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golearn.domain.CourseViewer;
import com.golearn.repository.CourseViewerRepository;

@Service
public class CourseViewerService {
	@Autowired
	private CourseViewerRepository courseViewerRepository;
	
	// 수강 완료
	public void save(CourseViewer request) {
		courseViewerRepository.save(request.getMbrNo(), request.getCosNo(), request.getIdxNo());
	}
}
