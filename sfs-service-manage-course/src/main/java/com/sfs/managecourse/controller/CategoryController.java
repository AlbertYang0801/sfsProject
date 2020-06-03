package com.sfs.managecourse.controller;

import com.sfs.api.course.CategoryControllerApi;
import com.sfs.framework.domain.course.Category;
import com.sfs.framework.domain.course.ext.CategoryNode;
import com.sfs.managecourse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分类管理
 *
 * @author yjw
 * @date 2019/11/11
 */
@RestController
@RequestMapping("/course/category")
public class CategoryController implements CategoryControllerApi {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Override
    public CategoryNode findCategoryList() {
        return categoryService.findCategoryList();
    }

    @GetMapping("/get/{id}")
    public Category getCategoryById(@PathVariable("id")String id){
        return categoryService.getCategory(id);
    }

}
