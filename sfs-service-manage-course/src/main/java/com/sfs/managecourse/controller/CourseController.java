package com.sfs.managecourse.controller;

import com.sfs.api.course.CourseControllerApi;
import com.sfs.framework.domain.course.*;
import com.sfs.framework.domain.course.ext.CourseView;
import com.sfs.framework.domain.course.ext.TeachplanNode;
import com.sfs.framework.domain.course.request.CourseListRequest;
import com.sfs.framework.domain.course.response.CoursePublishResult;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.framework.utils.SfsOauth2Util;
import com.sfs.framework.web.BaseController;
import com.sfs.managecourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程管理
 *
 * @author yjw
 * @date 2019/11/8
 */
@RestController
@RequestMapping("/course")
public class CourseController extends BaseController implements CourseControllerApi {

    @Autowired
    private CourseService courseService;

    //***************************************************课程计划*************************************************************8

    /**
     * 我的课程-管理课程-课程计划树展示
     * 课程计划列表查询，加载树
     *
     * @param courseId
     * @return
     */
    @GetMapping("/teachPlan/list/{courseId}")
    @Override
    public TeachplanNode findTeachPlanList(@PathVariable("courseId") String courseId) {
        return courseService.findTeachPlanList(courseId);
    }

    /**
     * 我的课程-管理课程-课程计划
     * 添加课程计划
     *
     * @param teachplan
     * @return
     */
    @PostMapping("/teachPlan/add")
    @Override
    public ResponseResult addTeachPlan(@RequestBody Teachplan teachplan) {
        return courseService.addTeachPlan(teachplan);
    }

    /**
     * 获取课程计划信息
     *
     * @param id
     * @return
     */
    @GetMapping("/teachPlan/get/{id}")
    @Override
    public Teachplan getTeachPlan(@PathVariable("id") String id) {
        return courseService.getTeachPlan(id);
    }

    /**
     * 更新课程计划信息
     *
     * @param teachplan
     * @return
     */
    @PutMapping("/teachPlan/update")
    @Override
    public ResponseResult updateTeachPlan(@RequestBody Teachplan teachplan) {
        return courseService.updateTeachPlan(teachplan);
    }

    /**
     * 删除课程计划信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/teachPlan/delete/{id}")
    @Override
    public ResponseResult deleteTeachPlan(@PathVariable("id") String id) {
        return courseService.deleteTeachPlan(id);
    }

    //***************************************************我的课程*************************************************************8

    /**
     * 我的课程-课程列表展示
     * 课程列表信息查询
     *
     * @param page
     * @param size
     * @param courseListRequest
     * @return
     */
//    @PreAuthorize("hasAnyAuthority('course_find_list')")
    @GetMapping("/coursebase/list/{page}/{size}")
    @Override
    public QueryResponseResult findCourseList(@PathVariable("page") int page,
                                              @PathVariable("size") int size,
                                              CourseListRequest courseListRequest) {
        SfsOauth2Util sfsOauth2Util = new SfsOauth2Util();
        //从请求头中获取jwt令牌，解析jwt令牌获取用户信息
        SfsOauth2Util.UserJwt userJwtFromHeader = sfsOauth2Util.getUserJwtFromHeader(request);
        //获取用户的教育机构id
//        String companyId = userJwtFromHeader.getCompanyId();
        String companyId = null;
        return courseService.findCourseList(companyId, page, size, courseListRequest);
    }

    /**
     * 我的课程-新增课程
     * 添加课程信息
     *
     * @param courseBase
     * @return
     */
    @PostMapping("/coursebase/add")
    @Override
    public ResponseResult addCourseBase(@RequestBody CourseBase courseBase) {
        return courseService.addCourseBase(courseBase);
    }

    /**
     * 我的课程-管理课程-基本信息
     * 课程信息的查询，数据回显
     *
     * @param courseId
     * @return
     */
    @GetMapping("/coursebase/get/{courseId}")
    @Override
    public CourseBase getCourseBaseByCourseId(@PathVariable("courseId") String courseId) {
        return courseService.getCourseBaseByCourseId(courseId);
    }

    /**
     * 我的课程-管理课程-基本信息
     * 课程信息的编辑
     *
     * @param id
     * @param courseBase
     * @return
     */
    @PutMapping("/coursebase/update/{id}")
    @Override
    public ResponseResult updateCourseBase(@PathVariable("id") String id, @RequestBody CourseBase courseBase) {
        return courseService.updateCourseBase(id, courseBase);
    }

    /**
     * 我的课程-管理课程-课程营销
     * 获取课程营销信息
     *
     * @param courseId
     * @return
     */
    @GetMapping("/coursemarket/get/{courseId}")
    @Override
    public CourseMarket getCourseMarketById(@PathVariable("courseId") String courseId) {
        return courseService.getCourseMarketById(courseId);
    }

    /**
     * 我的课程-管理课程-课程营销
     * 添加课程营销信息
     *
     * @param courseId
     * @param courseMarket
     * @return
     */
    @PutMapping("/coursemarket/update/{courseId}")
    @Override
    public ResponseResult updateCourseMarket(@PathVariable("courseId") String courseId, @RequestBody CourseMarket courseMarket) {
        return courseService.updateCourseMarket(courseId, courseMarket);
    }

    /**
     * 我的课程-课程图片-添加图片
     *
     * @param courseId
     * @param pic
     * @return
     */
    @PostMapping("/coursepic/add")
    @Override
    public ResponseResult addCoursePic(@RequestParam("courseId") String courseId, @RequestParam("pic") String pic) {
        return courseService.addCoursePic(courseId, pic);
    }

    /**
     * 根据课程id获取课程图片信息
     *
     * @param courseId
     * @return
     */
//    @PreAuthorize("hasAnyAuthority('course_find_pic')")
    @GetMapping("/coursepic/list/{courseId}")
    @Override
    public CoursePic findCoursePic(@PathVariable("courseId") String courseId) {
        return courseService.findCoursePic(courseId);
    }

    /**
     * 根据课程id删除课程图片信息
     * 不删除图片文件，只删除课程图片记录
     *
     * @param courseId
     * @return
     */
    @DeleteMapping("/coursepic/delete")
    @Override
    public ResponseResult deleteCoursePic(@RequestParam("courseId") String courseId) {
        return courseService.deleteCoursePic(courseId);
    }

    /**
     * 根据课程id加载课程详情数据
     *
     * @param id
     * @return
     */
    @GetMapping("/courseview/{id}")
    @Override
    public CourseView getCourseView(@PathVariable("id") String id) {
        return courseService.getCourseView(id);
    }

    /**
     * 课程详情界面预览
     *
     * @param id
     * @return
     */
    @PostMapping("/preview/{id}")
    @Override
    public CoursePublishResult previewCourse(@PathVariable("id") String id) {
        return courseService.previewCourse(id);
    }

    /**
     * 课程详情界面发布
     * 根据课程id创建页面信息，然后远程调用cms发布页面，返回页面url
     *
     * @param id
     * @return1
     */
    @PostMapping("/publish/{id}")
    @Override
    public CoursePublishResult publish(@PathVariable("id") String id) {
        return courseService.publish(id);
    }

    /**
     * 将课程计划与媒资绑定，保存到数据库
     *
     * @param teachplanMedia
     * @return
     */
    @PostMapping("/savemedia")
    @Override
    public ResponseResult saveMedia(@RequestBody TeachplanMedia teachplanMedia) {
        return courseService.saveMedia(teachplanMedia);
    }

    /**
     * 获取课程发布信息
     * @param courseId
     * @return
     */
    @GetMapping("/coursepub/get/{courseId}")
    public CoursePub getCoursePub(@PathVariable("courseId") String courseId){
        return courseService.getCoursePub(courseId);
    }

    /**
     * 推荐课程
     * @param learnings
     * @return
     */
    @GetMapping("/recommend/{learnings}")
    public List<CoursePub> recommendCourse(@PathVariable("learnings") String learnings){
        return courseService.recommendCourse(learnings);
    }

}


//@RequestBody 从请求体中将json数据转换为实体类
//localhost:8080/course/add/110?courseId=123
//?前面是请求路径,即localhost:8080/course/add/110;获取110参数需要使用@PathVariable注解
//?后面是请求参数,想要获取请求参数值需要使用@RequestParam注解