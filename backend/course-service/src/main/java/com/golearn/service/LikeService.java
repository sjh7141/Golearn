package com.golearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golearn.repository.LikeReqository;

@Service
public class LikeService {
	@Autowired
	private LikeReqository likeReqository;
	
	// 좋아요
	public void save(String mbrNo, long cosNo) {
		likeReqository.save(Long.parseLong(mbrNo), cosNo);
	}
	
	// 좋아요 취소
	public void delete(String mbrNo, long cosNo) {
		likeReqository.delete(Long.parseLong(mbrNo), cosNo);
	}
}
