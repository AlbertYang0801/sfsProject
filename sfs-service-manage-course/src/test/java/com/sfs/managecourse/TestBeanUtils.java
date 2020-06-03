package com.sfs.managecourse;

import com.sfs.framework.domain.course.CourseBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yjw
 * @date 2020/3/27 15:21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestBeanUtils {


    @Test
    public void testCopyProperties(){
        CourseBase courseBase= new CourseBase();
        courseBase.setId("1");
        courseBase.setGrade("200");
        courseBase.setName("第一个");


        CourseBase courseBaseNew = new CourseBase();
        courseBaseNew.setId("2");
        courseBaseNew.setName("第二个");
        courseBaseNew.setStudymodel("100");
        BeanUtils.copyProperties(courseBase,courseBaseNew);
        System.out.println(courseBase);
        System.out.println(courseBaseNew);

    }


}
