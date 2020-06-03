package com.sfs.learning.dao;

import com.sfs.framework.domain.learning.SfsLearningCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yjw
 * @date 2020/5/18 1:01
 */
public interface SfsLearningCourseRepository extends JpaRepository<SfsLearningCourse,String> {

        List<SfsLearningCourse> findByUserIdOrderByStartTime(String userId);

        int deleteByUserIdAndCourseId(String userId,String courseId);

}


