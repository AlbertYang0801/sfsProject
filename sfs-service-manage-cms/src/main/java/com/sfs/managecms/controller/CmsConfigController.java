package com.sfs.managecms.controller;

import com.sfs.api.cms.CmsConfigControllerApi;
import com.sfs.framework.domain.cms.CmsConfig;
import com.sfs.managecms.service.CmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置管理
 * @author  yjw
 * on 2019/9/19
 */
@RestController
@RequestMapping("/cms/config")
public class CmsConfigController implements CmsConfigControllerApi {

    @Autowired
    private CmsConfigService cmsConfigService;

    /**
     * 根据id获得页面配置所需的数据
     * @param id
     * @return
     */
    @Override
    @GetMapping("/getModel/{id}")
    public CmsConfig getModel(@PathVariable("id") String id) {
        return cmsConfigService.getCmsConfigById(id);
    }

}
