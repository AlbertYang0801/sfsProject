package com.sfs.api.search;

import com.sfs.framework.domain.course.CoursePub;
import com.sfs.framework.domain.course.TeachplanMediaPub;
import com.sfs.framework.domain.search.CourseSearchParam;
import com.sfs.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

/**
 * Es搜索相关的接口
 *
 * @author yjw
 * @date 2020/3/27 21:50
 */
@Api(value = "搜索相关接口", tags = {"搜索相关接口"})
public interface EsCourseControllerApi {

    /**
     * 课程搜索接口，支持分页
     * 传入的参数为查询参数实体类
     *
     * @param page
     * @param size
     * @param courseSearchParam
     * @return
     */
    @ApiOperation("课程搜索")
    @ApiImplicitParams({                    //描述一个类的参数列表
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    QueryResponseResult<CoursePub> list(int page, int size, CourseSearchParam courseSearchParam);

    /**
     * 根据课程id查询课程信息
     * 从索引库中查询课程相关数据
     * @param courseId
     * @return
     */
    @ApiOperation("根据id查询课程信息")
    Map<String,CoursePub> getAllById(String courseId);

    /**
     * 根据课程计划id查询媒资信息
     * 供学习中心工程调用
     * @param teachplanId
     * @return
     */
    @ApiOperation("根据课程计划id查询媒资信息")
    TeachplanMediaPub getMedia(String teachplanId);


}
