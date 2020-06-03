package com.sfs.managecms.dao;

import com.sfs.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author  yjw
 * on 2019/9/19
 */
public interface CmsConfigRepository extends MongoRepository<CmsConfig,String> {


}
