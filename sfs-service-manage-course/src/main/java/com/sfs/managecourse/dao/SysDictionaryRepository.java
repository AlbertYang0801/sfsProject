package com.sfs.managecourse.dao;

import com.sfs.framework.domain.system.SysDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 分类管理
 * MongoDB数据库表sys_dictionary
 * @author  yjw
 * on 2019/11/12
 */
public interface SysDictionaryRepository extends MongoRepository<SysDictionary,String> {

    /**
     * 根据字典分类查询数据字典信息
     * @param dType
     * @return
     */
    SysDictionary findBydType(String dType);

}
