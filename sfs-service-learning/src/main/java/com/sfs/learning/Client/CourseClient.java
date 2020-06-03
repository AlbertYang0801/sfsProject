package com.sfs.learning.Client;

import com.sfs.framework.client.SfsServiceList;
import com.sfs.framework.domain.course.Category;
import com.sfs.framework.domain.course.CourseBase;
import com.sfs.framework.domain.course.CoursePub;
import com.sfs.framework.domain.course.Teachplan;
import com.sfs.framework.domain.system.SysDictionary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author yjw
 * @date 2020/5/18 1:37
 */
@FeignClient(value = SfsServiceList.SFS_SERVICE_MANAGE_COURSE)
public interface CourseClient {

    @GetMapping("/course/teachPlan/get/{teachplanId}")
    Teachplan getTeachplan(@PathVariable("teachplanId") String teachplanId);

    @GetMapping("/course/coursebase/get/{courseId}")
    CourseBase getCourseBase(@PathVariable("courseId") String courseId);

    @GetMapping("/course/coursepub/get/{courseId}")
    CoursePub getCoursePub(@PathVariable("courseId") String courseId);

    @GetMapping("/course/recommend/{learnings}")
    List<CoursePub> recommendCourse(@PathVariable("learnings")String learnings);

    @GetMapping("/course/category/get/{id}")
    Category getCategoryById(@PathVariable("id")String id);


}
