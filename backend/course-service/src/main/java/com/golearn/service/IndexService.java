package com.golearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golearn.domain.Index;
import com.golearn.repository.IndexRepository;

@Service
public class IndexService {
	@Autowired
	private IndexRepository indexRepository;
	
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
		return indexRepository.findByCosNo(cosNo);
	}
	
	// 목차 상세 정보
	public Index findById(long idxNo) {
		return indexRepository.findById(idxNo);
	}
}
