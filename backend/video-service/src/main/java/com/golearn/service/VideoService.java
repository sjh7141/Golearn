package com.golearn.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.golearn.exception.UnAuthorizationException;
import com.golearn.model.Maker;
import com.golearn.model.Member;
import com.golearn.model.Tag;
import com.golearn.model.Video;
import com.golearn.model.VideoCompositeKey;
import com.golearn.model.VideoLike;
import com.golearn.model.VideoTag;
import com.golearn.repository.MemberRepository;
import com.golearn.repository.TagRepository;
import com.golearn.repository.VideoLikeRepository;
import com.golearn.repository.VideoRepository;
import com.golearn.repository.VideoTagRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VideoService {
    private final VideoRepository videoRepository;

    private final VideoLikeRepository videoLikeRepository;

    private final TagRepository tagRepository;
    
    private final VideoTagRepository videoTagRepository;

    private final MemberRepository memberRepository;

    VideoService(VideoRepository videoRepository, VideoLikeRepository videoLikeRepository, TagRepository tagRepository, MemberRepository memberRepository, VideoTagRepository videoTagRepository) {
        this.videoRepository = videoRepository;
        this.videoLikeRepository = videoLikeRepository;
        this.tagRepository = tagRepository;
        this.memberRepository = memberRepository;
        this.videoTagRepository = videoTagRepository;
    }

    public Map getVideo(int vidNo) {
        if (videoRepository.existsById(vidNo)) {
            Map<String, Object> map = new HashMap<>();
            videoRepository.addViewCount(vidNo);
            Video video = videoRepository.findById(vidNo).get();
            video.setTags(tagRepository.findAllAndTagByVidNo(video.getVidNo()));
            video.setVidLikes(videoLikeRepository.countDistinctByVideoCompositekey_VidNo(vidNo));
            map.put("video", video);

            int vidPno = video.getVidPno();
            List<Maker> list = new LinkedList<>();
            Member tmpMember;

            while(vidPno!=0){
                tmpMember = memberRepository.findById(video.getMbrNo()).get();
                list.add(new Maker(tmpMember.getMbrNo(),tmpMember.getMbrNickname(),tmpMember.getMbrProfile()));
                video = videoRepository.findById(vidPno).get();
                vidPno = video.getVidPno();
            }
            map.put("editor",list);
            tmpMember = memberRepository.findById(video.getMbrNo()).get();
            map.put("author",new Maker(tmpMember.getMbrNo(),tmpMember.getMbrNickname(),tmpMember.getMbrProfile()));
            return map;
        }
        return null;
    }

    public void hideVideo(List<Integer> vidNos, int mbrNo) {
        if (videoRepository.hideVideo(vidNos, mbrNo) == 0) {
            throw new UnAuthorizationException();
        }
    }

    public Video saveVideo(Video video) {
        video =videoRepository.saveAndFlush(video);
    	return video;
    }
    
    public void saveTag(List<Tag> tag, int vidNo) {
    	for(Tag cur : tag) {
    		VideoTag video = new VideoTag();
    		video.setVidNo(vidNo);
    		video.setTagNo(cur.getTagNo());
    		videoTagRepository.saveAndFlush(video);
    	}
    }

    public Map isLikeVideo(int vidNo, int mbrNo) {
        Map<String, Boolean> result = new HashMap();
        result.put("isLike", videoLikeRepository.existsById(new VideoCompositeKey(vidNo, mbrNo)));
        return result;
    }

    public void likeVideo(int vidNo, int mbrNo) {
        VideoCompositeKey videoCompositekey = new VideoCompositeKey(vidNo, mbrNo);
        if (!videoLikeRepository.existsById(videoCompositekey)) {
            VideoLike videoLike = new VideoLike(videoCompositekey);
            videoLikeRepository.save(videoLike);
        }
    }

    public void unlikeVideo(int vidNo, int mbrNo) {
        VideoCompositeKey videoCompositekey = new VideoCompositeKey(vidNo, mbrNo);
        if (videoLikeRepository.existsById(videoCompositekey)) {
            videoLikeRepository.deleteById(videoCompositekey);
        }
    }

    public List<Video> getLikeVideo(int mbrNo) {
        return videoRepository.findAllLikeVideo(mbrNo);
    }

    public List<Video> getVideos(int mbrNo) {
        List<Video> videos = videoRepository.findAllByMbrNoAndVidHideFalse(mbrNo,Sort.by("regDt").descending());
        for (Video video : videos) {
            video.setTags(tagRepository.findAllAndTagByVidNo(video.getVidNo()));
        }
        return videos;
    }
}

