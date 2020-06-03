package com.sfs.managecourse.service;

import com.sfs.framework.domain.course.Category;
import com.sfs.framework.domain.course.ext.CategoryNode;
import com.sfs.managecourse.dao.CategoryMapper;
import com.sfs.managecourse.dao.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 分类管理
 * @author yjw
 * @date 2019/11/12
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 获取分类信息列表
     * @return
     */
    public CategoryNode findCategoryList() {
        return categoryMapper.findCategoryList();
    }

    public Category getCategory(String id){
        Optional<Category> optional = categoryRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }


}
