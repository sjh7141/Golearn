package com.golearn.service;


import com.golearn.domain.*;
import com.golearn.repository.CourseRepository;
import com.golearn.repository.CourseViewerRepository;
import com.golearn.repository.MemberRepository;
import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
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
    private final CourseViewerRepository courseViewerRepository;
    private final DataSource dataSource;
    private final MemberRepository memberRepository;
    CourseRecommendService(CourseRepository courseRepository,DataSource dataSource,MemberRepository memberRepository, CourseViewerRepository courseViewerRepository){
        this.courseRepository = courseRepository;
        this.dataSource = dataSource;
        this.memberRepository = memberRepository;
        this.courseViewerRepository = courseViewerRepository;
    }

    public List<Course> getRecommendCourse(int mbrNo) throws TasteException, IOException {

        FastByIDMap<PreferenceArray> userData = new FastByIDMap<>();
        List<Member> members = memberRepository.findAll();
        for(Member member : members){
            List<CoursePrefer> coursePrefers = courseRepository.getPrefer(member.getMbrNo());
            if(coursePrefers.size()==0){
                continue;
            }
            List<GenericPreference> genericPreferences = new LinkedList<>();
            for(CoursePrefer coursePrefer : coursePrefers){
                genericPreferences.add(new GenericPreference(coursePrefer.getMbrNo(),coursePrefer.getCosNo(),coursePrefer.getValue()));
            }
            userData.put(member.getMbrNo(),new GenericUserPreferenceArray(genericPreferences));
        }

        ClassPathResource resource = new ClassPathResource("file.csv");
//        DataModel model = new GenericDataModel(userData);
        DataModel model = new FileDataModel(resource.getFile());
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
//        for (LongPrimitiveIterator it = model.getUserIDs(); it.hasNext(); ) {
//            for(LongPrimitiveIterator it2 = model.getUserIDs(); it2.hasNext(); ){
//                System.out.println(""+it.peek()+" "+it2.peek());
//                log.info("============>"+similarity.userSimilarity(it.peek(),it2.nextLong()));
//            }
//            it.nextLong();
//        }
        ParallelSGDFactorizer factorizer = new ParallelSGDFactorizer(model,1,0.1,1);
//        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(-1, similarity, model);
//        Recommender recommender = new GenericItemBasedRecommender(model,similarity);
        SVDRecommender recommender = new SVDRecommender(model, factorizer);
        List<RecommendedItem> recommendedItems = recommender.recommend(mbrNo,1);
        List<Course> list = new LinkedList<>();

        for(RecommendedItem recommendedItem : recommendedItems){
            System.out.println(recommendedItem);
            list.add(courseRepository.findById(recommendedItem.getItemID()));
        }

//        for(Member member : members){
//            for(Member member1 : members){
//                log.info("==>"+similarity.userSimilarity(member.getMbrNo(),member1.getMbrNo()));
//            }
//        }
//        ClassPathResource resource = new ClassPathResource("file.csv");
//
//        DataModel dm = new FileDataModel(resource.getFile());
//
//        log.info("file read");
//        UserSimilarity similarity = new PearsonCorrelationSimilarity(dm);

//        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dm);
//        UserBasedRecommender recommender = new GenericUserBasedRecommender(dm, neighborhood, similarity);
//        List<RecommendedItem> recommendations = recommender.recommend(2, 2);
//        for (RecommendedItem recommendation : recommendations) {
//            System.out.println(recommendation);
//        }
        return list;
    }

    private List<CoursePreference> mapCoursePreference(List<CourseViewer> courseViewers){
        List<CoursePreference> coursePreferences = new LinkedList<>();
        for(CourseViewer courseViewer : courseViewers){
            coursePreferences.add(new CoursePreference(courseViewer.getMbrNo(),courseViewer.getCosNo()));
        }
        return coursePreferences;
    }
    private List<GenericPreference> mapPreference(List<CourseViewer> courseViewers){
        List<GenericPreference> genericPreferences = new LinkedList<>();
        for(CourseViewer courseViewer : courseViewers){
            genericPreferences.add(new GenericPreference(courseViewer.getMbrNo(),courseViewer.getCosNo(),1));
        }
        return genericPreferences;
    }
}
