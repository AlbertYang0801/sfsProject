package com.sfs.learning.dao;

import com.sfs.framework.domain.learning.SfsLearningList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yjw
 * @date 2020/5/18 1:06
 */
public interface SfsLearningListRepository extends JpaRepository<SfsLearningList,String> {


    List<SfsLearningList> findByUserIdAndCourseId(String userId,String courseId);

    int deleteByUserIdAndCourseId(String userId,String courseId);

    List<SfsLearningList> findByUserIdOrderByStartTime(String userId);


}
