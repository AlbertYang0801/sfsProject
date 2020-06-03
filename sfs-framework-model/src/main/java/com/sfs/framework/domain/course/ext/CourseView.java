package com.sfs.framework.domain.course.ext;

import com.sfs.framework.domain.course.CourseBase;
import com.sfs.framework.domain.course.CourseMarket;
import com.sfs.framework.domain.course.CoursePic;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 课程详情页面数据模型
 * @NoArgsConstructor lombok的无参构造注解
 * @author yjw
 * @date 2019/11/26 10:39
 */
@Data
@ToString
@NoArgsConstructor
public class CourseView {

    /**
     * 课程基本信息
     */
    CourseBase courseBase;

    /**
     * 课程营销信息
     */
    CourseMarket courseMarket;

    /**
     * 课程图片信息
     */
    CoursePic coursePic;

    /**
     * 课程教学计划
     */
    TeachplanNode teachplanNode;


}
