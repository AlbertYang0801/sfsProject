package com.sfs.api.course;

import com.sfs.framework.domain.course.CourseFormulate;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author yjw
 * @date 2020/5/11 23:10
 */
@Api("定制课程路线")
public interface CourseformulateControllerApi {

    /**
     * 添加定制课程路线
     * @param courseFormulate
     * @return
     */
    @ApiOperation("添加定制课程基本信息")
    ResponseResult addCourseFormulate(CourseFormulate courseFormulate);

    @ApiOperation("获取课程学习路线列表")
    QueryResponseResult getCourseFormulateList(int page,int size);

    @ApiOperation("根据id获取课程路线信息")
    CourseFormulate getCourseFormulateById(String id);




}
