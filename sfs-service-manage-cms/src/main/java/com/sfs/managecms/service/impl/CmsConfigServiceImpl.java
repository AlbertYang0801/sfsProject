package com.sfs.managecms.service.impl;

import com.sfs.framework.domain.cms.CmsConfig;
import com.sfs.managecms.dao.CmsConfigRepository;
import com.sfs.managecms.service.CmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @author  yjw
 * on 2019/9/19
 */
@Service
public class CmsConfigServiceImpl implements CmsConfigService {

    @Autowired
    private CmsConfigRepository cmsConfigRepository;

    /**
     * 根据id获取cmsConfig数据
     * @param id
     * @return
     */
    @Override
    public CmsConfig getCmsConfigById(String id) {
        //根据id查询Cms配置信息
        Optional<CmsConfig> optional = cmsConfigRepository.findById(id);
        if (optional.isPresent()){
            CmsConfig cmsConfig = optional.get();
            return cmsConfig;
        }
        return null;
    }

}
