package com.sfs.framework.domain.course.ext;

import com.sfs.framework.domain.course.CourseBase;
import lombok.Data;
import lombok.ToString;

/**
 * 课程管理的扩展查询结果实体类
 * 根据连接查询，查询出课程对应的图片信息
 * @author  admin on 2018/2/10.
 */
@Data
@ToString
public class CourseInfo extends CourseBase {

    /**
     * 课程相关图片
     */
    private String pic;

}
