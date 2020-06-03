package com.sfs.managecourse.controller;

import com.sfs.api.course.SysDictionaryControllerApi;
import com.sfs.framework.domain.system.SysDictionary;
import com.sfs.managecourse.service.SysDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据字典管理
 *
 * @author yjw
 * @date 2019/11/12
 */
@RestController
@RequestMapping("/course/sys/dictionary")
public class SysDictionaryController implements SysDictionaryControllerApi {

    @Autowired
    private SysDictionaryService sysDictionaryService;

    /**
     * 根据数据字典类型查询字典数据
     * @param type
     * @return
     */
    @GetMapping("/get/{dType}")
    @Override
    public SysDictionary findBydType(@PathVariable("dType") String type) {
        return sysDictionaryService.findBydType(type);
    }


}
