package com.golearn.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golearn.domain.Index;
import com.golearn.domain.VideoManager;
import com.golearn.domain.VideoVersioningResopnse;
import com.golearn.repository.IndexRepository;
import com.golearn.repository.VideoManagerRepository;

@Service
public class VideoManagerService {
	@Autowired
	VideoManagerRepository videoManagerRepository;
	@Autowired
	IndexRepository indexRepository;

	// 영상 요청 하기
	public VideoManager requestVideo(String mbrNo, VideoManager request) {
		request.setMbrReqNo(Long.parseLong(mbrNo));
		return videoManagerRepository.save(request);
	}

	// 영상 요청 승인|거부
	public int updateVideo(String mbrNo, VideoManager request) {
		request.setRegDt(new Date());
		request.setMbrAdminNo(Long.parseLong(mbrNo));
		createVersioning(request);
		return videoManagerRepository.updateRequest(request.getMbrAdminNo(),request.getVidReqAcceptYn(),request.getVidResComment(),request.getVidReqNo());
	}

	// 영상 요청 승인 시 버저닝 생성
	private void createVersioning(VideoManager request) {
		if (request.getVidReqAcceptYn() == 1) { // 승인 되었을 때
			videoManagerRepository.createVersioning(request.getIdxNo(), request.getVidNo());
			
			Index index = indexRepository.findById(request.getIdxNo());
			index.setIdxNo(request.getIdxNo());
			index.setCosNo(request.getCosNo());
			index.setVidNo(request.getVidNo());
			indexRepository.save(index);
		}
	}
	
	// 영상 버저닝 가져오기
	public List<VideoVersioningResopnse> getVersioning(long idxNo){
		return videoManagerRepository.getVersioning(idxNo);
	}
	
	// 영상 요청 목록
	public List<VideoManager> perPageBy20(long cosNo, int page) {
		page = (page - 1) * 20;
		List<VideoManager> videoManagers = videoManagerRepository.perPageBy20(cosNo, page);
		return videoManagers;
	}
}
