package com.sfs.managecms.service;

import com.sfs.framework.domain.cms.CmsTemplate;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;

/**
 * @author  by yjw
 * on 2019/9/14
 */
public interface CmsTemplateService {

    QueryResponseResult findTemplate();

    QueryResponseResult findTemplateList(int page, int size, String templateName);

    ResponseResult addTemplate(CmsTemplate cmsTemplate);

    ResponseResult deleteTemplateById(String templateId);

    CmsTemplate getTemplateById(String templateId);

    ResponseResult updateTemplate(String templateId, CmsTemplate cmsTemplate);

}
