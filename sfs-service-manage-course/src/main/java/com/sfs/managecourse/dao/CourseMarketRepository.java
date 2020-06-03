package com.sfs.managecourse.dao;

import com.sfs.framework.domain.course.CourseMarket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 课程营销
 * @author yjw
 * @date 16:47
 */
public interface CourseMarketRepository extends JpaRepository<CourseMarket,String> {

}
