package com.sfs.managecms.dao;

import com.sfs.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by yjw
 * on 2019/9/14
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {



}
