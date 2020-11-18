package com.golearn.service;


import com.golearn.domain.*;
import com.golearn.mapper.CourseMapper;
import com.golearn.mapper.TagMapper;
import com.golearn.repository.CourseRepository;
import com.golearn.repository.CourseViewerRepository;
import com.golearn.repository.LikeReqository;
import com.golearn.repository.MemberRepository;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.netflix.discovery.converters.Auto;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.GenericBooleanPrefDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;

import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.svd.ParallelSGDFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import sun.security.util.ArrayUtil;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
@Service
@Slf4j
public class CourseRecommendService {

    private final CourseRepository courseRepository;

    private final MemberRepository memberRepository;

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private TagMapper tagMapper;
    CourseRecommendService(CourseRepository courseRepository,MemberRepository memberRepository){
        this.courseRepository = courseRepository;
        this.memberRepository = memberRepository;
    }

    public List<Course> getRecommendCourse(int mbrNo) throws TasteException, IOException {
        List<Course> list = new LinkedList<>();
        List<Long> cosList = new LinkedList<>();
        if(mbrNo==-1){
            for(Course course : courseRepository.getBestCourseByCourseList(4)){
                course.setLikeCount(courseMapper.findCourseLikeCount(course.getCosNo()));
                course.setViewerCount(courseMapper.findCourseViewerCount(course.getCosNo()));
                course.setTags(tagMapper.findByCosNo((int)course.getCosNo()));
                course.setMbrNickname(memberRepository.findById((int)course.getMbrNo()).get().getMbrNickname());
                list.add(course);
            }
            return list;
        }
        FastByIDMap<PreferenceArray> userData = new FastByIDMap<>();
        List<Member> members = memberRepository.findAll();
        for(Member member : members){
            List<CoursePrefer> coursePrefers = courseRepository.getPrefer(member.getMbrNo());

            List<GenericPreference> genericPreferences = new LinkedList<>();
            for(CoursePrefer coursePrefer : coursePrefers){
                genericPreferences.add(new GenericPreference(coursePrefer.getMbrNo(),coursePrefer.getCosNo(),coursePrefer.getValue()));
            }
            userData.put(member.getMbrNo(),new GenericUserPreferenceArray(genericPreferences));
        }

        DataModel model = new GenericDataModel(userData);
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(-1234123, similarity, model);
        Recommender recommender = new GenericUserBasedRecommender(model,neighborhood,similarity);
        List<RecommendedItem> recommendedItems = recommender.recommend(mbrNo,4);


        for(RecommendedItem recommendedItem : recommendedItems){
            cosList.add(recommendedItem.getItemID());
            list.add(courseRepository.findById(recommendedItem.getItemID()));
        }
        if(cosList.size()==0){
            list.addAll(courseRepository.getBestCourseByCourseList(4));
        }
        else{
            list.addAll(courseRepository.getBestCourse(cosList, 4-list.size()));
        }
        for(Course course : list){
            course.setLikeCount(courseMapper.findCourseLikeCount(course.getCosNo()));
            course.setViewerCount(courseMapper.findCourseViewerCount(course.getCosNo()));
            course.setTags(tagMapper.findByCosNo((int)course.getCosNo()));
            course.setMbrNickname(memberRepository.findById((int)course.getMbrNo()).get().getMbrNickname());
        }
        return list;
    }

    public List<Course> getHitCourse() {
        List<Course> list = new LinkedList<>();
        for(Course course : courseRepository.getHitCourse()){
            course.setLikeCount(courseMapper.findCourseLikeCount(course.getCosNo()));
            course.setViewerCount(courseMapper.findCourseViewerCount(course.getCosNo()));
            course.setTags(tagMapper.findByCosNo((int)course.getCosNo()));
            course.setMbrNickname(memberRepository.findById((int)course.getMbrNo()).get().getMbrNickname());
            list.add(course);
        }
        return list;
    }

    public List<Course> getIncreaseCourse() {
        List<Course> list = new LinkedList<>();
        for(Course course : courseRepository.getIncreaseCourse()){
            course.setLikeCount(courseMapper.findCourseLikeCount(course.getCosNo()));
            course.setViewerCount(courseMapper.findCourseViewerCount(course.getCosNo()));
            course.setTags(tagMapper.findByCosNo((int)course.getCosNo()));
            course.setMbrNickname(memberRepository.findById((int)course.getMbrNo()).get().getMbrNickname());
            list.add(course);
        }
        return list;
    }
}
