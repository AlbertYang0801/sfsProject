package com.sfs.search.service;

import com.sfs.framework.domain.course.CoursePub;
import com.sfs.framework.domain.course.TeachplanMediaPub;
import com.sfs.framework.domain.search.CourseSearchParam;
import com.sfs.framework.model.response.QueryResponseResult;

import java.util.Map;

/**
 * @author yjw
 * @date 2020/3/27 22:05
 */
public interface EsCourseService {

    /**
     * 课程查询，支持分页
     * 根据查询条件进行查询
     *
     * @param page
     * @param size
     * @param courseSearchParam
     * @return
     */
    QueryResponseResult<CoursePub> list(int page, int size, CourseSearchParam courseSearchParam);

    /**
     * 根据课程id查询课程信息
     * 从索引库中获取课程的相关信息，用于视频播放课程计划的加载
     * @param courseId
     * @return
     */
    Map<String, CoursePub> getAllById(String courseId);

    /**
     * 根据课程计划id从索引库中获取课程计划媒资信息
     * @param teachplanId
     * @return
     */
    TeachplanMediaPub getMedia(String teachplanId);

}
