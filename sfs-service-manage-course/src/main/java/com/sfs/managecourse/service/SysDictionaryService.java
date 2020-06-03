package com.sfs.managecourse.service;

import com.sfs.framework.domain.system.SysDictionary;

/**
 * 数据字典管理
 *
 * @author yjw
 * @date 2019/11/12
 */
public interface SysDictionaryService {

    /**
     * 根据数据字典类型获取分类数据
     *
     * @param type
     * @return
     */
    SysDictionary findBydType(String type);

}
