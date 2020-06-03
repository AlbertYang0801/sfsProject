package com.sfs.learning.controller;

import com.sfs.api.learning.CourseLearningControllerApi;
import com.sfs.framework.domain.course.CoursePub;
import com.sfs.framework.domain.learning.ext.SfsLearningCourseExt;
import com.sfs.framework.domain.learning.ext.SfsLearningListExt;
import com.sfs.framework.domain.learning.response.GetMediaResult;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.learning.Client.CourseClient;
import com.sfs.learning.service.CourseLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学习中心
 *
 * @author yjw
 * @date 2020/4/25 20:09
 */
@RestController
@RequestMapping("/learning/course")
public class CourseLearningController implements CourseLearningControllerApi {

    @Autowired
    private CourseLearningService courseLearningService;

    /**
     * 根据课程id和课程计划id获取媒资文件播放路径
     *
     * @param courseId
     * @param teachplanId
     * @return
     */
    @GetMapping("/getmedia/{courseId}/{teachplanId}")
    @Override
    public GetMediaResult getMedia(@PathVariable("courseId") String courseId, @PathVariable("teachplanId") String teachplanId) {
        return courseLearningService.getMedia(courseId, teachplanId);
    }

    /**
     * 添加学习进度
     *
     * @param userId
     * @param courseId
     * @param teachplanId
     * @return
     */
    @PostMapping("/addlearning/{userId}/{courseId}/{teachplanId}")
    @Override
    public ResponseResult addLearning(@PathVariable("userId") String userId,
                                      @PathVariable("courseId") String courseId,
                                      @PathVariable("teachplanId") String teachplanId) {
        return courseLearningService.addLearning(userId, courseId, teachplanId);
    }

    /**
     * 获取用户学习过的视频
     *
     * @param userId
     * @return
     */
    @GetMapping("/getlearning/{userId}")
    @Override
    public List<SfsLearningCourseExt> getLearningCourseList(@PathVariable("userId") String userId) {
        return courseLearningService.getLearningCourseList(userId);
    }

    /**
     * 收藏课程
     *
     * @param userId
     * @param courseId
     * @return
     */
    @PostMapping("/collect/{userId}/{courseId}")
    @Override
    public ResponseResult collectCourse(@PathVariable("userId") String userId, @PathVariable("courseId") String courseId) {
        return courseLearningService.collectCourse(userId, courseId);
    }

    /**
     * 获取用户收藏的课程
     *
     * @param userId
     * @return
     */
    @GetMapping("/getcollect/{userId}")
    @Override
    public List<SfsLearningListExt> getCollectCourseList(@PathVariable("userId") String userId) {
        return courseLearningService.getCollectCourseList(userId);
    }

    /**
     * 取消收藏
     *
     * @param userId
     * @param courseId
     * @return
     */
    @PostMapping("/deletecollect/{userId}/{courseId}")
    @Override
    public ResponseResult deleteCollect(@PathVariable("userId") String userId, @PathVariable("courseId") String courseId) {
        return courseLearningService.delectCollect(userId, courseId);
    }

    /**
     * 推荐课程
     *
     * @param userId
     * @return
     */
    @GetMapping("/recommend/{userId}")
    @Override
    public List<CoursePub> recommendCourse(@PathVariable("userId") String userId) {
        return courseLearningService.recommendCourse(userId);
    }





}
