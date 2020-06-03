package com.sfs.api.course;

import com.sfs.framework.domain.course.*;
import com.sfs.framework.domain.course.ext.CourseView;
import com.sfs.framework.domain.course.ext.TeachplanNode;
import com.sfs.framework.domain.course.request.CourseListRequest;
import com.sfs.framework.domain.course.response.CoursePublishResult;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 课程管理接口
 *
 * @author yjw
 * on 2019/11/8
 */

@Api(value = "课程管理接口", tags = {"课程信息管理"})
public interface CourseControllerApi {

    /**
     * 课程计划查询
     * @param courseId
     * @return
     */
    @ApiOperation("课程计划查询")
    TeachplanNode findTeachPlanList(String courseId);

    /**
     * 添加课程计划
     * @param teachplan
     * @return
     */
    @ApiOperation("添加课程计划")
    ResponseResult addTeachPlan(Teachplan teachplan);

    /**
     * 获取课程计划
     * @param id
     * @return
     */
    @ApiOperation("获取课程计划")
    Teachplan getTeachPlan(String id);

    /**
     * 修改课程计划
     * @param teachplan
     * @return
     */
    @ApiOperation("修改课程计划")
    ResponseResult updateTeachPlan(Teachplan teachplan);

    /**
     * 删除课程计划
     * @param id
     * @return
     */
    @ApiOperation("删除课程计划")
    ResponseResult deleteTeachPlan(String id);

    /**
     * 获取我的课程-课程列表
     * 分页查询
     * @param page
     * @param size
     * @param courseListRequest
     * @return
     */
    @ApiOperation("我的课程课程列表")
    QueryResponseResult findCourseList(int page, int size, CourseListRequest courseListRequest);

    /**
     * 新增课程
     * @param courseBase
     * @return
     */
    @ApiOperation(("添加课程"))
    ResponseResult addCourseBase(CourseBase courseBase);

    /**
     * 根据课程id查询课程信息
     * @param courseId
     * @return
     */
    @ApiOperation("根据课程id查询课程信息")
    CourseBase getCourseBaseByCourseId(String courseId);

    /**
     * 更新课程信息
     * @param id
     * @param courseBase
     * @return
     */
    @ApiOperation("更新课程信息")
    ResponseResult updateCourseBase(String id, CourseBase courseBase);

    /**
     * 查询课程营销信息
     * @param courseId
     * @return
     */
    @ApiOperation("查询课程营销信息")
    CourseMarket getCourseMarketById(String courseId);

    /**
     * 更新课程营销信息
     * @param courseId
     * @param courseMarket
     * @return
     */
    @ApiOperation("更新课程营销信息")
    ResponseResult updateCourseMarket(String courseId, CourseMarket courseMarket);

    /**
     * 添加课程图片信息
     * @param courseId
     * @param pic
     * @return
     */
    @ApiOperation("添加课程图片")
    ResponseResult addCoursePic(String courseId, String pic);

    /**
     * 查询课程图片信息
     * @param courseId
     * @return
     */
    @ApiOperation("查询课程图片信息")
    CoursePic findCoursePic(String courseId);

    /**
     * 删除课程图片信息
     * @param courseId
     * @return
     */
    @ApiOperation("删除课程图片信息")
    ResponseResult deleteCoursePic(String courseId);

    /**
     * 获取课程详情页面数据
     * @param id
     * @return
     */
    @ApiOperation("课程详情页面数据加载")
    CourseView getCourseView(String id);

    /**
     * 指定课程详情页面预览
     * @param id
     * @return
     */
    @ApiOperation("预览课程详情")
    CoursePublishResult previewCourse(String id);

    /**
     * 课程发布到本地
     * @param id
     * @return
     */
    @ApiOperation("发布课程")
    CoursePublishResult publish(String id);

    /**
     * 保存课程计划对应的媒资信息
     * @param teachplanMedia
     * @return
     */
    @ApiOperation("保存媒资")
    ResponseResult saveMedia(TeachplanMedia teachplanMedia);

}
