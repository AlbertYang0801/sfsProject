package com.sfs.managecms.dao;

import com.sfs.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by yjw
 * on 2019/9/14
 */
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate,String> {


}
