package com.golearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golearn.domain.Like;
import com.golearn.repository.LikeReqository;

@Service
public class LikeService {
	@Autowired
	private LikeReqository likeReqository;
	
	// 좋아요
	public void save(String mbrNo, long cosNo) {
		Like like = new Like();
		like.setMbrNo(Long.parseLong(mbrNo));
		like.setCosNo(cosNo);
		likeReqository.save(like);
	}
	
	// 좋아요 취소
	public void delete(String mbrNo, long cosNo) {
		likeReqository.deleteByMbrNoAndCosNo(Long.parseLong(mbrNo), cosNo);
	}
}
