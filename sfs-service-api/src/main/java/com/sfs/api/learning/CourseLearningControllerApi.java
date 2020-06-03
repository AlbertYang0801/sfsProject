package com.sfs.api.learning;

import com.sfs.framework.domain.course.CoursePub;
import com.sfs.framework.domain.learning.ext.SfsLearningCourseExt;
import com.sfs.framework.domain.learning.ext.SfsLearningListExt;
import com.sfs.framework.domain.learning.response.GetMediaResult;
import com.sfs.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 在线学习接口
 * @author yjw
 * @date 2020/4/25 18:31
 */
public interface CourseLearningControllerApi {

    /**
     * 根据课程id和课程计划id获取视频播放地址
     * @param courseId
     * @param teachplanId
     * @return
     */
    @ApiOperation("获取课程的学习地址")
    public GetMediaResult getMedia(String courseId, String teachplanId);


    @ApiOperation("添加课程学习进度")
    ResponseResult addLearning(String userId,String courseId,String teachplanId);


    @ApiOperation("获取用户学习的视频")
    List<SfsLearningCourseExt> getLearningCourseList(String userId);

    @ApiOperation("收藏课程")
    ResponseResult collectCourse(String userId,String courseId);

    @ApiOperation("获取收藏课程列表")
    List<SfsLearningListExt> getCollectCourseList(String userId);

    @ApiOperation("取消收藏")
    ResponseResult deleteCollect(String userId,String courseId);

    @ApiOperation("推荐课程")
    List<CoursePub> recommendCourse(String userId);

}
