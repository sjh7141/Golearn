package com.golearn.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.golearn.domain.Index;

public interface IndexRepository extends CrudRepository<Index, Long>{
	// 코스 목록 조회
	List<Index> findByCosNo(long cosNo);
	
	// 목차 상세 정보 조회
	Index findById(long idxNo);
}
