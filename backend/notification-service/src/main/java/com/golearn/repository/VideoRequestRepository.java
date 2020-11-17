package com.golearn.repository;

import com.golearn.model.VideoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRequestRepository extends JpaRepository<VideoRequest, Integer> {

}
