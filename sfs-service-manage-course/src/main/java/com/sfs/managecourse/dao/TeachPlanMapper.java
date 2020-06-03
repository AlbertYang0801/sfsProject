package com.sfs.managecourse.dao;

import com.sfs.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;


/**
 * 课程计划
 * @author yjw
 * on 2019/11/8
 */
@Mapper
public interface TeachPlanMapper {

    /**
     * 根据课程id查询课程计划列表
     * 根据父id查询排列成树结构
     * @param courseId
     * @return
     */
    TeachplanNode selectListByCourseId(String courseId);

}
