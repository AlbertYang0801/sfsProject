package com.sfs.api.course;

import com.sfs.framework.domain.course.ext.CategoryNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 课程分类接口
 *
 * @author yjw
 * on 2019/11/11
 */
@Api(value = "课程分类管理", tags = {"课程分类管理"})
public interface CategoryControllerApi {

    /**
     * 查询分类列表
     *
     * @return
     */
    @ApiOperation("查询分类列表")
    CategoryNode findCategoryList();

}
