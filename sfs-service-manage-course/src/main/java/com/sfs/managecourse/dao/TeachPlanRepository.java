package com.sfs.managecourse.dao;

import com.sfs.framework.domain.course.Teachplan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 课程计划
 * @author  yjw
 * on 2019/11/8
 */
public interface TeachPlanRepository extends JpaRepository<Teachplan,String> {

    /**
     * 根据课程id和父id查找课程计划，可以查出根节点
     * @param courseId
     * @param parentId
     * @return
     */
    List<Teachplan> findByCourseidAndParentid(String courseId,String parentId);

    /**
     * 根据父id查询课程计划列表
     * @param parentId
     * @return
     */
    List<Teachplan> findByParentid(String parentId);

}
