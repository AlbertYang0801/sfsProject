package com.sfs.managecourse.dao;

import com.sfs.framework.domain.course.ext.CategoryNode;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程分类
 * @author  yjw
 * on 2019/11/12
 */
@Mapper
public interface CategoryMapper {

    /**
     * 获取分类信息列表
     * @return
     */
    CategoryNode findCategoryList();

}
