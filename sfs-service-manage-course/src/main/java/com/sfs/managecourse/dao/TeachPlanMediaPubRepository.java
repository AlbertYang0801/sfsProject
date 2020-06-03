package com.sfs.managecourse.dao;

import com.sfs.framework.domain.course.TeachplanMediaPub;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 课程计划媒资信息发布表
 * @author yjw
 * @date 2020/4/24 20:50
 */
public interface TeachPlanMediaPubRepository extends JpaRepository<TeachplanMediaPub,String> {

    /**
     * 根据课程id删除课程计划的媒资信息
     * 删除成功返回1
     * 删除失败返回0
     */
    long deleteByCourseId(String courseId);

}
