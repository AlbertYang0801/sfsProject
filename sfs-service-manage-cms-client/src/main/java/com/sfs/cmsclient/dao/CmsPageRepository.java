package com.sfs.cmsclient.dao;

import com.sfs.framework.domain.cms.CmsPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 定义CmsPage的dao层操作
 * 操作Mongodb数据库继承MongoRepository<实体类，主键类型>
 * @author yjw
 * @create 2019/8/16 16:46
 */
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {



}


