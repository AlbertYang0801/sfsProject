package com.sfs.managecourse.service;

import com.sfs.framework.domain.course.*;
import com.sfs.framework.domain.course.ext.CourseView;
import com.sfs.framework.domain.course.ext.TeachplanNode;
import com.sfs.framework.domain.course.request.CourseListRequest;
import com.sfs.framework.domain.course.response.CoursePublishResult;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;

import java.util.List;

/**
 * 课程管理
 * @author yjw
 * @date 2019/11/8
 */
public interface CourseService {

    /**
     * 根据课程id获取课程信息
     * @param courseId
     * @return
     */
    CourseBase findCourseById(String courseId);

    /**
     * 根据课程id查询课程计划列表
     * @param courseId
     * @return
     */
    TeachplanNode findTeachPlanList(String courseId);

    /**
     * 添加课程计划
     * @param teachPlan
     * @return
     */
    ResponseResult addTeachPlan(Teachplan teachPlan);

    /**
     * 获取课程计划信息
     * @param id
     * @return
     */
    Teachplan getTeachPlan(String id);

    /**
     * 更新课程计划信息
     * @param teachplan
     * @return
     */
    ResponseResult updateTeachPlan(Teachplan teachplan);

    /**
     * 删除课程计划信息
     * @param id
     * @return
     */
    ResponseResult deleteTeachPlan(String id);

    /**
     * 根据条件分页查询课程信息列表
     * @param page
     * @param size
     * @param courseListRequest
     * @return
     */
    QueryResponseResult findCourseList(String companyId,int page, int size, CourseListRequest courseListRequest);

    /**
     * 添加课程信息
     * @param courseBase
     * @return
     */
    ResponseResult addCourseBase(CourseBase courseBase);

    /**
     * 根据课程id获取课程信息
     * @param courseId
     * @return
     */
    CourseBase getCourseBaseByCourseId(String courseId);

    /**
     * 更新课程信息
     * @param id
     * @param courseBase
     * @return
     */
    ResponseResult updateCourseBase(String id,CourseBase courseBase);

    /**
     * 获取课程营销信息
     * @param courseId
     * @return
     */
    CourseMarket getCourseMarketById(String courseId);

    /**
     * 更新课程营销信息
     * @param courseId
     * @param courseMarket
     * @return
     */
    ResponseResult updateCourseMarket(String courseId,CourseMarket courseMarket);

    /**
     * 添加上传成功后的图片地址和对应得课程id
     * @param courseId
     * @param pic
     * @return
     */
    ResponseResult addCoursePic(String courseId, String pic);

    /**
     * 根据课程id获取课程图片信息
     * @param courseId
     * @return
     */
    CoursePic findCoursePic(String courseId);

    /**
     * 根据课程id删除课程图片信息
     * @param courseId
     * @return
     */
    ResponseResult deleteCoursePic(String courseId);

    /**
     * 课程详情界面数据加载
     * @param id
     * @return
     */
    CourseView getCourseView(String id);

    /**
     * 课程详情页面的预览
     * @param id
     * @return
     */
    CoursePublishResult previewCourse(String id);

    /**
     * 根据课程id一键发布课程详情界面
     * @param id
     * @return
     */
    CoursePublishResult publish(String id);

    /**
     * 将课程计划与对应的媒资文件绑定的信息存到数据库
     * @param teachplanMedia
     * @return
     */
    ResponseResult saveMedia(TeachplanMedia teachplanMedia);


    CoursePub getCoursePub(String courseId);

    /**
     * 根据用户学习推荐课程
     * @param
     * @return
     */
    List<CoursePub> recommendCourse(String learnings);


}
