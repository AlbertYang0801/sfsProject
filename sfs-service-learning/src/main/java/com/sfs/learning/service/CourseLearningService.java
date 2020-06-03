package com.sfs.learning.service;

import com.alibaba.fastjson.JSON;
import com.sfs.framework.domain.course.*;
import com.sfs.framework.domain.learning.SfsLearningCourse;
import com.sfs.framework.domain.learning.SfsLearningList;
import com.sfs.framework.domain.learning.ext.SfsLearningCourseExt;
import com.sfs.framework.domain.learning.ext.SfsLearningListExt;
import com.sfs.framework.domain.learning.response.GetMediaResult;
import com.sfs.framework.domain.learning.response.LearningCode;
import com.sfs.framework.exception.ExceptionCast;
import com.sfs.framework.model.response.CommonCode;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.learning.Client.CourseClient;
import com.sfs.learning.Client.CourseSearchClient;
import com.sfs.learning.dao.SfsLearningCourseRepository;
import com.sfs.learning.dao.SfsLearningListRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 学习中心
 *
 * @author yjw
 * @date 2020/4/25 20:10
 */
@Service
public class CourseLearningService {

    @Autowired
    private CourseSearchClient courseSearchClient;

    @Autowired
    private SfsLearningCourseRepository sfsLearningCourseRepository;

    @Autowired
    private SfsLearningListRepository sfsLearningListRepository;

    @Autowired
    CourseClient courseClient;


    public GetMediaResult getMedia(String courseId, String teachplanId) {
        //校验学生的学习权限

        //调用搜索接口，从索引库中获取课程计划媒资信息
        TeachplanMediaPub teachplanMediaPub = courseSearchClient.getMedia(teachplanId);
        if (teachplanMediaPub == null || StringUtils.isEmpty(teachplanMediaPub.getMediaUrl())) {
            //提示获取视频播放地址出错
            ExceptionCast.cast(LearningCode.LEARNING_GET_MEDIA_ERROR);
        }
        //返回媒资视频路径
        return new GetMediaResult(CommonCode.SUCCESS, teachplanMediaPub.getMediaUrl());
    }

    /**
     * 添加学习记录
     *
     * @param userId
     * @param courseId
     * @param teachplanId
     * @return
     */
    @Transactional
    public ResponseResult addLearning(String userId, String courseId, String teachplanId) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(courseId) || StringUtils.isEmpty(teachplanId)) {
            return new ResponseResult(CommonCode.FAIL);
        }
        //删除该用户该课程之前的学习记录
        sfsLearningCourseRepository.deleteByUserIdAndCourseId(userId, courseId);
        SfsLearningCourse sfsLearningCourse = new SfsLearningCourse();
        sfsLearningCourse.setCourseId(courseId);
        sfsLearningCourse.setUserId(userId);
        sfsLearningCourse.setTeachplanId(teachplanId);
        sfsLearningCourse.setStartTime(new Date());
        //添加该用户新的学习记录
        sfsLearningCourseRepository.save(sfsLearningCourse);
        return new ResponseResult(CommonCode.SUCCESS);
    }


    /**
     * 获取用户学习过的视频
     *
     * @param userId
     * @return
     */
    public List<SfsLearningCourseExt> getLearningCourseList(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        //查找学习记录
        List<SfsLearningCourse> list = sfsLearningCourseRepository.findByUserIdOrderByStartTime(userId);
        List<SfsLearningCourseExt> dataList = new ArrayList<>();
        for (SfsLearningCourse sfsLearningCourse : list) {
            SfsLearningCourseExt sfsLearningCourseExt = new SfsLearningCourseExt();
            BeanUtils.copyProperties(sfsLearningCourse, sfsLearningCourseExt);
            //获取课程信息
            CourseBase courseBase = courseClient.getCourseBase(sfsLearningCourse.getCourseId());
            sfsLearningCourseExt.setCourseName(courseBase.getName());
            //获取章节信息
            Teachplan teachplan = courseClient.getTeachplan(sfsLearningCourse.getTeachplanId());
            sfsLearningCourseExt.setTeachplanName(teachplan.getPname());
            dataList.add(sfsLearningCourseExt);
        }
        return dataList;
    }

    /**
     * 收藏课程
     *
     * @param userId
     * @param courseId
     * @return
     */
    public ResponseResult collectCourse(String userId, String courseId) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(courseId)) {
            return new ResponseResult(CommonCode.FAIL);
        }
        //查询课程是否已经收藏
        List<SfsLearningList> list = sfsLearningListRepository.findByUserIdAndCourseId(userId, courseId);
        if (list.size() > 0) {
            //提示课程已经收藏
            return new ResponseResult(LearningCode.LEARNING_COURSE_EXISIT);
        }
        SfsLearningList sfsLearningList = new SfsLearningList();
        sfsLearningList.setCourseId(courseId);
        sfsLearningList.setUserId(userId);
        sfsLearningList.setStartTime(new Date());
        sfsLearningList.setStatus("0");
        sfsLearningListRepository.save(sfsLearningList);
        return new ResponseResult();
    }

    /**
     * 获取收藏列表
     * @param userId
     * @return
     */
    public List<SfsLearningListExt> getCollectCourseList(String userId) {
        List<SfsLearningList> list = sfsLearningListRepository.findByUserIdOrderByStartTime(userId);
        if(list.size()<=0){
            return null;
        }
        List<SfsLearningListExt> dataList = new ArrayList<>();
        for (SfsLearningList sfsLearningList : list) {
            CoursePub coursePub = courseClient.getCoursePub(sfsLearningList.getCourseId());
            SfsLearningListExt sfsLearningListExt = new SfsLearningListExt();
            BeanUtils.copyProperties(sfsLearningList,sfsLearningListExt);
            sfsLearningListExt.setCourseName(coursePub.getName());
            sfsLearningListExt.setPic(coursePub.getPic());
            dataList.add(sfsLearningListExt);
        }
        return dataList;
    }

    /**
     * 取消收藏课程
     */
    @Transactional
    public ResponseResult delectCollect(String userId, String courseId) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(courseId)) {
            return new ResponseResult(CommonCode.FAIL);
        }
        sfsLearningListRepository.deleteByUserIdAndCourseId(userId, courseId);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 推荐课程
     * @return
     */
    public  List<CoursePub> recommendCourse(String userId){
        HashSet<String> hashSet = new HashSet();
        //获取用户学习视频
        List<SfsLearningCourse>  learningLists= sfsLearningCourseRepository.findByUserIdOrderByStartTime(userId);
        //获取用户收藏视频
        List<SfsLearningList> collectLists = sfsLearningListRepository.findByUserIdOrderByStartTime(userId);
        //根据用户学习视频和收藏视频分类获取视频信息
        if(learningLists.size()>0){
            for (SfsLearningCourse learningList : learningLists) {
                hashSet.add(learningList.getCourseId());
            }
        }
        if(collectLists.size()>0){
            for (SfsLearningList collectList : collectLists) {
                hashSet.add(collectList.getCourseId());
            }
        }
        //将set集合转换为json
        String idsString = JSON.toJSONString(hashSet);
        //获取推荐课程
        List<CoursePub> coursePubs = courseClient.recommendCourse(idsString);
        //替换课程分类
        for (CoursePub coursePub : coursePubs) {
            String mtName = courseClient.getCategoryById(coursePub.getMt()).getLabel();
            String stName = courseClient.getCategoryById(coursePub.getSt()).getLabel();
            coursePub.setMt(mtName);
            coursePub.setSt(stName);
        }
        return coursePubs;
    }





}
