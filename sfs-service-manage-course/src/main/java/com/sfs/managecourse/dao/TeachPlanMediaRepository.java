package com.sfs.managecourse.dao;

import com.sfs.framework.domain.course.TeachplanMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 课程计划和媒资绑定信息表
 * @author yjw
 * @date 2020/4/18 23:07
 */
public interface TeachPlanMediaRepository extends JpaRepository<TeachplanMedia,String> {

    /**
     * 根据课程id查询课程的课程计划媒资信息
     * @param courseId
     * @return
     */
    List<TeachplanMedia> findByCourseId(String courseId);


}
