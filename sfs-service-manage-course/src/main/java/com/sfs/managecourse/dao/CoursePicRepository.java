package com.sfs.managecourse.dao;

import com.sfs.framework.domain.course.CoursePic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 课程图片
 * @author yjw
 * @date 2019/11/18 16:51
 */
public interface CoursePicRepository extends JpaRepository<CoursePic,String> {

    /**
     * 根据courseId删除课程图片信息
     * 删除成功返回1
     * 删除失败返回0
     * @param courseid
     * @return
     */
    long deleteByCourseid(String courseid);

}
