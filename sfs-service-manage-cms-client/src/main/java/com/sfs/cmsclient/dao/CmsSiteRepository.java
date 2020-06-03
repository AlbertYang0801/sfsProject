package com.sfs.cmsclient.dao;

import com.sfs.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 定义CmsSite的dao层操作
 * @author  yjw
 * on 2019/9/14
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {


}
