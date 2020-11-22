package com.golearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golearn.domain.Course;
import com.golearn.mapper.CourseMapper;
import com.golearn.repository.LikeReqository;

@Service
public class LikeService {
	@Autowired
	private LikeReqository likeReqository;

	@Autowired
	private CourseMapper courseMapper;

	// 좋아요
	public void save(String mbrNo, long cosNo) {
		likeReqository.save(Long.parseLong(mbrNo), cosNo);
	}
	// 좋아요 취소
	public void delete(String mbrNo, long cosNo) {
		likeReqository.delete(Long.parseLong(mbrNo), cosNo);
	}

	public List<Course> findByLike(int userNo) {
		return courseMapper.findByLike(userNo);
	}

	public List<Course> findByViewer(int userNo) {
		return courseMapper.findByViewer(userNo);
	}

	public int checkLike(int userNo, int cosNo) {
		return courseMapper.checkLike(userNo, cosNo);
	}
}
