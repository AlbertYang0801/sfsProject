package com.sfs.managecourse.dao;

import com.github.pagehelper.Page;
import com.sfs.framework.domain.course.ext.CourseInfo;
import com.sfs.framework.domain.course.request.CourseListRequest;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程信息
 * @author yjw
 */
@Mapper
public interface CourseBaseMapper {

   /**
    * 分页查询课程信息
    * 请求参数为扩展的查询条件（教育机构id）
    * @param courseListRequest
    * @return
    */
   Page<CourseInfo> findCourseListPage(CourseListRequest courseListRequest);

}
