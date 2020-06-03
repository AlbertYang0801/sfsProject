package com.sfs.managecms.service;

import com.sfs.framework.domain.cms.CmsConfig;

/**
 * @author yjw
 * on 2019/9/19
 */
public interface CmsConfigService {

    /**
     * 根据id获取cmsConfig数据
     * @param id
     * @return
     */
    CmsConfig getCmsConfigById(String id);

}
