package com.sfs.managecourse.dao;

import com.sfs.framework.domain.course.CourseBase;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 课程信息
 * @author Administrator.
 */
public interface CourseBaseRepository extends JpaRepository<CourseBase,String> {


}
