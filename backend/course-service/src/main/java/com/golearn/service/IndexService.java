package com.golearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golearn.domain.Course;
import com.golearn.domain.Index;
import com.golearn.mapper.CourseMapper;
import com.golearn.repository.IndexRepository;

@Service
public class IndexService {
	@Autowired
	private IndexRepository indexRepository;
	
	@Autowired
	private CourseMapper courseMapper;
	
	// 목차 생성
	public void saveAll(List<Index> indexs) {
		indexRepository.saveAll(indexs);
	}
	
	// 목차 수정
	public void updateAll(List<Index> indexs) {
		indexRepository.saveAll(indexs);
	}
	
	// 목차 삭제
	public void deleteAll(List<Index> indexs) {
		for(Index index: indexs) {
			indexRepository.deleteById(index.getIdxNo());
		}
	}
	
	// 목차 조회
	public List<Index> findByCosNo(long cosNo){
		return indexRepository.findByCosNoOrderByIdxOrderAsc(cosNo);
	}
	
	// 목차 상세 정보
	public Index findById(long idxNo) {
		return indexRepository.findById(idxNo);
	}
	
	public int checkIndex(int userNo, int idxNo) {
		return courseMapper.checkIndex(userNo, idxNo);
	}

}
