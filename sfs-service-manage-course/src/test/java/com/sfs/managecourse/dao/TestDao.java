package com.sfs.managecourse.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sfs.framework.domain.course.CourseBase;
import com.sfs.framework.domain.course.ext.CourseInfo;
import com.sfs.framework.domain.course.ext.TeachplanNode;
import com.sfs.framework.domain.course.request.CourseListRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDao {

    @Autowired
    CourseBaseRepository courseBaseRepository;

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Autowired
    TeachPlanMapper teachPlanMapper;

    @Test
    public void testCourseBaseRepository(){
        Optional<CourseBase> optional = courseBaseRepository.findById("402885816240d276016240f7e5000002");
        if(optional.isPresent()){
            CourseBase courseBase = optional.get();
            System.out.println(courseBase);
        }

    }

    @Test
    public void testCourseMapper(){
        Optional<CourseBase> optional = courseBaseRepository.findById("402885816240d276016240f7e5000002");
        CourseBase courseBase = optional.get();
        System.out.println(courseBase);

    }

    @Test
    public void testTeachPlanMapper(){
        TeachplanNode teachplanNode = teachPlanMapper.selectListByCourseId("4028e581617f945f01617f9dabc40000");
        System.out.println(teachplanNode);
    }

    @Test
    public void testPageHelper(){
        PageHelper.startPage(1,10);
        CourseListRequest courseListRequest = new CourseListRequest();
        courseListRequest.setCompanyId("1");
        Page<CourseInfo> courseListPage = courseBaseMapper .findCourseListPage(courseListRequest);
        List<CourseInfo> list = courseListPage.getResult();
        System.out.println(list);
    }

}
