package com.sfs.search.controller;

import com.sfs.api.search.EsCourseControllerApi;
import com.sfs.framework.domain.course.CoursePub;
import com.sfs.framework.domain.course.TeachplanMediaPub;
import com.sfs.framework.domain.search.CourseSearchParam;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.search.service.EsCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 课程搜索实体类
 * @author yjw
 * @date 2020/3/27 21:58
 */
@RestController
@RequestMapping("/search/course")
public class EsCourseController implements EsCourseControllerApi {

    @Autowired
    private EsCourseService esCourseService;

    /**
     * 根据请求参数进行搜索课程信息
     * 支持分页
     * @param page
     * @param size
     * @param courseSearchParam
     * @return
     */
    @GetMapping("/list/{page}/{size}")
    @Override
    public QueryResponseResult<CoursePub> list(@PathVariable("page") int page, @PathVariable("size") int size,
                                              CourseSearchParam courseSearchParam) {
        return esCourseService.list(page,size,courseSearchParam);
    }

    /**
     * 根据课程id从索引库中查询课程信息
     * @param courseId
     * @return
     */
    @GetMapping("/getall/{courseId}")
    @Override
    public Map<String, CoursePub> getAllById(@PathVariable("courseId") String courseId) {
        return esCourseService.getAllById(courseId);
    }

    /**
     * 根据课程计划id从课程计划媒资索引库中搜索课程计划媒资信息
     * @param teachplanId
     * @return
     */
    @GetMapping("/getmedia/{teachplanId}")
    @Override
    public TeachplanMediaPub getMedia(@PathVariable("teachplanId") String teachplanId) {
        return esCourseService.getMedia(teachplanId);
    }


}
