package com.sfs.managecourse.dao;

import com.sfs.framework.domain.course.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yjw
 * @date 2020/5/18 22:35
 */
public interface CategoryRepository extends JpaRepository<Category,String> {

}
