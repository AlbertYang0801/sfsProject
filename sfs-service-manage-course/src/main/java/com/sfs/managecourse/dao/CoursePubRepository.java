package com.sfs.managecourse.dao;

import com.sfs.framework.domain.course.CoursePub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 课程发布索引
 * @author yjw
 * @date 2020/3/27 0:51
 */
public interface CoursePubRepository extends JpaRepository<CoursePub,String> {

    List<CoursePub> findByGradeAndMtAndSt(String grade,String mt,String st);

    List<CoursePub> findByMtAndSt(String mt,String st);

    List<CoursePub> findByGradeAndMt(String grade,String mt);

    List<CoursePub> findByGradeOrderByPubTime(String grade);

}
